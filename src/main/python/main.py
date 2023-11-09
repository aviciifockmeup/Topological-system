import sys
import random
import time

from scapy.asn1.asn1 import ASN1_OID
from scapy.contrib.igmp import IGMP
from scapy.layers.dhcp import BOOTP, DHCP
from scapy.layers.dns import DNS, DNSQR
from scapy.layers.inet import IP, UDP, ICMP, TCP
from scapy.layers.ipsec import ESP
from scapy.layers.l2 import Ether, ARP, GRE
from scapy.layers.sctp import SCTP
from scapy.layers.snmp import SNMP, SNMPget, SNMPvarbind
from scapy.layers.tftp import TFTP
from scapy.packet import Raw
from scapy.sendrecv import srp, sr1


class Tracer:
    def __init__(self):
        self.__packet_funcs = {
            'ICMP': self.__ICMPPacket,
            'TCP': self.__TCPPacket,
            'UDP': self.__UDPPacket,
            'APP': self.__APPPacket,
        }
        self.protocols = [
            'ICMP',
            'TCP',
            'UDP',
            'APP'
        ]

    def __ICMPPacket(self, target_ip: str, ttl: int):
        packet = {
            # type = 8 echo request
            'ICMP_EQ': IP(dst=target_ip, ttl=ttl) / ICMP(type=8),
            # type = 0 echo reply
            'ICMP_ER': IP(dst=target_ip, ttl=ttl) / ICMP(type=0),
            # type = 8 echo request with data
            'ICMP_EQ_heavy': IP(dst=target_ip, ttl=ttl) / ICMP(type=8) / "Default",
            # type = 8 echo request with data
            'ICMP_ER_heavy': IP(dst=target_ip, ttl=ttl) / ICMP(type=0) / "Default",

            # timestamp request
            'ICMP_TIMESTAMP_Q': IP(dst=target_ip, ttl=ttl) / ICMP(type=13),
            # timestamp reply
            'ICMP_TIMESTAMP_R': IP(dst=target_ip, ttl=ttl) / ICMP(type=14),

            # deprecated
            # traceroute
            'ICMP_TRACEROUTE': IP(dst=target_ip, ttl=ttl) / ICMP(type=30)
        }
        return packet

    def __TCPPacket(self, target_ip: str, ttl: int):
        packet = {
            'SYN': IP(dst=target_ip, ttl=ttl) / TCP(dport=80, flags='S'),
            'ACK': IP(dst=target_ip, ttl=ttl) / TCP(dport=80, flags='A'),
            'FIN': IP(dst=target_ip, ttl=ttl) / TCP(dport=80, flags='F'),
            'RST': IP(dst=target_ip, ttl=ttl) / TCP(dport=80, flags='R')
        }
        return packet

    def __UDPPacket(self, target_ip: str, ttl: int):
        packet = {
            'UDP_VALID': IP(dst=target_ip, ttl=ttl) / UDP(dport=random.randint(1, 10000)),
            'UDP_INVALID': IP(dst=target_ip, ttl=ttl) / UDP(dport=30001)
        }
        return packet

    def __APPPacket(self, target_ip: str, ttl: int):
        packet = {
            'DNS': IP(dst=target_ip, ttl=ttl) / UDP(dport=53) / DNS(qd=DNSQR(qname="www.bing.com")),
            'HTTP': IP(dst=target_ip, ttl=ttl) / TCP(
                dport=80) / "GET / HTTP/1.1\r\nHost: www.example.com\r\nConnection: close\r\n\r\n",
            'SNMP': IP(dst=target_ip) / UDP(dport=161) / SNMP(
                PDU=SNMPget(varbindlist=[SNMPvarbind(oid=ASN1_OID('1.3.6.1.2.1.1.1.0'))])),
            'SMTP': IP(dst=target_ip) / TCP(dport=25) / Raw(load="EHLO bing.com"),
            'TFTP': IP(dst=target_ip, ttl=ttl) / UDP(dport=69) / TFTP(),
            'FTP': IP(dst=target_ip) / TCP(dport=21) / Raw(load="USER anonymous\r\nPASS \r\n")
        }
        return packet

    # 192.168.1.0/24 CIDR for param
    def ARPScan(self, ip_range: str):
        # 创建ARP请求包
        arp = ARP(pdst=ip_range)
        # 创建以太网广播帧
        ether = Ether(dst="ff:ff:ff:ff:ff:ff")
        packet = ether / arp
        result = srp(packet, timeout=2, verbose=0)[0]
        remote_result = []
        for sent, received in result:
            # 对于每个响应，将IP和MAC添加到`clients`列表
            remote_result.append({'ip': received.psrc,
                                  'mac': received.hwsrc})
        return remote_result

    def Uni_Traceroute(self, packet_func_name, target_ip: str, max_ttl, timeout):
        # 单一类型报文探测路由
        traceroute_result = []
        for ttl in range(1, max_ttl):
            flag = False
            packet_dict = self.__packet_funcs[packet_func_name](target_ip, ttl)
            for protocol, packet in packet_dict.items():
                send_time = time.time()
                node_reply = sr1(packet, timeout=timeout, verbose=0)
                if node_reply:
                    recv_time = time.time()
                    delay = round((recv_time - send_time) * 1000)
                    flag = True
#                     print(f'{ttl} {protocol} {node_reply.src} {hex(node_reply["IP"].id)} {delay} ms')
                    traceroute_result.append((ttl, node_reply.src, hex(node_reply["IP"].id), delay))
                    break
                else:
                    continue
            if not flag:
#                 print(f'{ttl} *')
                traceroute_result.append((ttl, '*'))
        return traceroute_result


if __name__ == "__main__":
    try:
        # 选用一个协议 'ICMP','TCP','UDP','APP'
        protocol = sys.argv[1]
        # 目的ip
        ip = sys.argv[2]
        # 最大指定跳数
        max_ttl = sys.argv[3]
        # 超时second
        timeout = sys.argv[4]
        t = Tracer()
        # result = [(第1跳, ip , ip编码, 时延),(第2跳, ip , ip编码, 时延)....(第n跳, ip , ip编码, 时延)]
        process = t.Uni_Traceroute(protocol, ip, int(max_ttl), float(timeout))
        # 取result每项ip  ips = [ip1, ip2, ip3,...]
        nodes = [t[1] for t in process]

        i = 0
        for index in range(len(nodes)):
            if nodes[index] == '*':
                nodes[index] = f'{nodes[index]}{i}'
                i += 1

        edges = [f'{nodes[i]},{nodes[i + 1]}' for i in range(len(nodes) - 1)]
        dict = {
#             "process": process,
            "node_list": nodes,
            "edge_list": edges
        }
        print(dict)
    except:
        print('ERROR')
