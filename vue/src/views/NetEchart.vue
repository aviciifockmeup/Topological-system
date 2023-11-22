<script>
import * as echarts from 'echarts'
// import computerImg from '@/assets/computer.png';
import firewallImg from '@/assets/firewall.png';
import hostImg from '@/assets/host.png';
import routerImg from '@/assets/router.png';
// import serverImg from '@/assets/server.png';
import switchImg from '@/assets/switch.png';
export default {
    data() {
        return {
            chart: null,
            // symbolImg:'image://'+ require('@/assets/router.png'),
            option: {
                "animation": true,
                "animationThreshold": 2000,
                "animationDuration": 1000,
                "animationEasing": "cubicOut",
                "animationDelay": 0,
                "animationDurationUpdate": 300,
                "animationEasingUpdate": "cubicOut",
                "animationDelayUpdate": 0,
                "aria": {
                    "enabled": false
                },
                "color": [
                    "#5470c6",
                    "#91cc75",
                    "#fac858",
                    "#ee6666",
                    "#73c0de",
                    "#3ba272",
                    "#fc8452",
                    "#9a60b4",
                    "#ea7ccc"
                ],
                "series": [
                    {
                        "type": "graph",
                        "layout": "force",
                        "symbolSize": 10,
                        "circular": {
                            "rotateLabel": false
                        },
                        "force": {
                            "repulsion": 1000,
                            "gravity": 0.2,
                            "edgeLength": 30,
                            "friction": 0.6,
                            "layoutAnimation": true
                        },
                        "label": {
                            "show": true,
                            "position": "top",
                            "color": "red",
                            "margin": 8,
                            "fontSize": 10
                        },
                        "lineStyle": {
                            "show": true,
                            "width": 1,
                            "opacity": 1,
                            "curveness": 0,
                            "type": "solid"
                        },
                        "roam": true,
                        "draggable": true,
                        "focusNodeAdjacency": false,
                        "data": [
                            {
                                "name": "219.85.125.245",
                                "fixed": false,
                                "symbol": 'image://' + switchImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "219.85.125.245",
                                "device": "switch",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Kaohsiung City"
                                },
                                "location": {
                                    "lat": "22.6272784",
                                    "lon": "120.3014353"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "Sony Network Taiwan Limited",
                                "aso": "Sony Network Taiwan Limited"
                            },
                            {
                                "name": "1.160.104.229",
                                "fixed": false,
                                "symbol": 'image://' + switchImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.160.104.229",
                                "device": "switch",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taoyuan City"
                                },
                                "location": {
                                    "lat": "24.9936281",
                                    "lon": "121.3009798"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "59.115.5.81",
                                "fixed": false,
                                "symbol": 'image://' + switchImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "59.115.5.81",
                                "device": "switch",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Hualien County"
                                },
                                "location": {
                                    "lat": "23.9871589",
                                    "lon": "121.6015714"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "59.127.254.11",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "59.127.254.11",
                                "device": "router",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Pingtung County"
                                },
                                "location": {
                                    "lat": "22.5519759",
                                    "lon": "120.5487597"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "36.236.195.8",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "36.236.195.8",
                                "device": "router",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Tainan City"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "36.231.164.151",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "36.231.164.151",
                                "device": "router",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taoyuan City"
                                },
                                "location": {
                                    "lat": "24.9936281",
                                    "lon": "121.3009798"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "122.117.25.3",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.117.25.3",
                                "device": "router",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taichung City"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "218.164.7.126",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "218.164.7.126",
                                "device": "router",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "218.164.32.120",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "218.164.32.120",
                                "device": "router",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "218.164.160.119",
                                "fixed": false,
                                "symbol": 'image://' + routerImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "218.164.160.119",
                                "device": "router",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "218.173.23.99",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "218.173.23.99",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "高雄市"
                                },
                                "location": {
                                    "lat": "22.6272784",
                                    "lon": "120.3014353"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "203.0.113.42",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "203.0.113.42",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "美国",
                                    "city": "纽约市"
                                },
                                "location": {
                                    "lat": "40.712776",
                                    "lon": "-74.005974"
                                },
                                "PoweredBy": "GeoIP",
                                "organization": "Verizon",
                                "aso": "Network Services"
                            },
                            {
                                "name": "77.88.55.66",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "77.88.55.66",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "俄罗斯",
                                    "city": "莫斯科"
                                },
                                "location": {
                                    "lat": "55.755825",
                                    "lon": "37.617635"
                                },
                                "PoweredBy": "MaxMind",
                                "organization": "Rostelecom",
                                "aso": "Internet Services"
                            },
                            {
                                "name": "123.45.67.89",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "123.45.67.89",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "加拿大",
                                    "city": "多伦多"
                                },
                                "location": {
                                    "lat": "43.653225",
                                    "lon": "-79.383186"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "Rogers Communications",
                                "aso": "Telecommunications"
                            },
                            {
                                "name": "202.156.12.34",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "202.156.12.34",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "新加坡",
                                    "city": "新加坡市"
                                },
                                "location": {
                                    "lat": "1.352083",
                                    "lon": "103.819836"
                                },
                                "PoweredBy": "MaxMind",
                                "organization": "Singtel",
                                "aso": "Telecom Services"
                            },
                            {
                                "name": "1.169.111.111",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.169.111.111",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台北市"
                                },
                                "location": {
                                    "lat": "25.0329636",
                                    "lon": "121.5654268"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.169.92.39",
                                "fixed": false,
                                "symbol": 'image://' + firewallImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.169.92.39",
                                "device": "firewall",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "新北市"
                                },
                                "location": {
                                    "lat": "25.0169826",
                                    "lon": "121.4627868"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.173.65.23",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.173.65.23",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.171.37.178",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.171.37.178",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台北市"
                                },
                                "location": {
                                    "lat": "25.0329636",
                                    "lon": "121.5654268"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.165.115.62",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.165.115.62",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台中市"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.169.134.9",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.169.134.9",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "新竹市"
                                },
                                "location": {
                                    "lat": "24.8138297",
                                    "lon": "120.9674752"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.168.90.211",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.168.90.211",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台中市"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.162.137.177",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.162.137.177",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "桃园市"
                                },
                                "location": {
                                    "lat": "24.9936281",
                                    "lon": "121.3009798"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "1.172.170.104",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "1.172.170.104",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "澎湖县"
                                },
                                "location": {
                                    "lat": "23.5711899",
                                    "lon": "119.5793157"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "111.241.148.140",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "111.241.148.140",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "新北市"
                                },
                                "location": {
                                    "lat": "25.0169826",
                                    "lon": "121.4627868"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "111.248.30.26",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "111.248.30.26",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "桃园市"
                                },
                                "location": {
                                    "lat": "24.9936281",
                                    "lon": "121.3009798"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "180.176.111.159",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "180.176.111.159",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台北市"
                                },
                                "location": {
                                    "lat": "25.0329636",
                                    "lon": "121.5654268"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "kbro CO.",
                                "aso": "kbro CO. Ltd."
                            },
                            {
                                "name": "122.118.6.123",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.118.6.123",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台中市"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "122.118.13.55",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.118.13.55",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台中市"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "122.146.69.24",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.146.69.24",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "新北市"
                                },
                                "location": {
                                    "lat": "25.0169826",
                                    "lon": "121.4627868"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "New Century InfoComm Tech Co.",
                                "aso": "New Century InfoComm Tech Co., Ltd."
                            },
                            {
                                "name": "122.121.118.209",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.121.118.209",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "122.121.192.34",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.121.192.34",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "122.121.30.227",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "122.121.30.227",
                                "device": "host",
                                "geoinfo": {
                                    "country": "中国",
                                    "city": "台南市"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "114.26.30.34",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "114.26.30.34",
                                "device": "host",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taipei City"
                                },
                                "location": {
                                    "lat": "24.1477358",
                                    "lon": "120.6736482"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "114.45.163.49",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "114.45.163.49",
                                "device": "host",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taipei City"
                                },
                                "location": {
                                    "lat": "25.0329636",
                                    "lon": "121.5654268"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "114.45.105.76",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "114.45.105.76",
                                "device": "host",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "New Taipei City"
                                },
                                "location": {
                                    "lat": "25.0169826",
                                    "lon": "121.4627868"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "114.44.15.175",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "114.44.15.175",
                                "device": "host",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Taipei City"
                                },
                                "location": {
                                    "lat": "25.0329636",
                                    "lon": "121.5654268"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            },
                            {
                                "name": "114.27.45.128",
                                "fixed": false,
                                "symbol": 'image://' + hostImg,
                                "symbolSize": 30,
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ip": "114.27.45.128",
                                "device": "host",
                                "geoinfo": {
                                    "country": "China",
                                    "city": "Tainan City"
                                },
                                "location": {
                                    "lat": "22.9998999",
                                    "lon": "120.2268758"
                                },
                                "PoweredBy": "IPIP",
                                "organization": "HiNet",
                                "aso": "Data Communication Business Group"
                            }
                        ],
                        "edgeLabel": {
                            "show": false,
                            "margin": 8
                        },
                        "edgeSymbol": [
                            null,
                            null
                        ],
                        "edgeSymbolSize": 10,
                        "links": [
                            {
                                "source": "219.85.125.245",
                                "target": "1.160.104.229",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.115.5.81",
                                "target": "219.85.125.245",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "219.85.125.245",
                                "target": "59.127.254.11",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "219.85.125.245",
                                "target": "36.236.195.8",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "1.160.104.229",
                                "target": "36.231.164.151",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "1.160.104.229",
                                "target": "122.117.25.3",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.115.5.81",
                                "target": "218.164.7.126",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.115.5.81",
                                "target": "218.164.32.120",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.115.5.81",
                                "target": "218.164.160.119",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.127.254.11",
                                "target": "36.236.195.8",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.236.195.8",
                                "target": "36.231.164.151",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.231.164.151",
                                "target": "122.117.25.3",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "122.117.25.3",
                                "target": "218.164.7.126",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.7.126",
                                "target": "218.164.32.120",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.32.120",
                                "target": "218.164.160.119",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.127.254.11",
                                "target": "218.173.23.99",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.236.195.8",
                                "target": "203.0.113.42",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.231.164.151",
                                "target": "77.88.55.66",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "122.117.25.3",
                                "target": "123.45.67.89",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.7.126",
                                "target": "202.156.12.34",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.32.120",
                                "target": "1.169.111.111",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.160.119",
                                "target": "1.169.92.39",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.127.254.11",
                                "target": "1.173.65.23",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.127.254.11",
                                "target": "1.171.37.178",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "59.127.254.11",
                                "target": "1.165.115.62",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.236.195.8",
                                "target": "1.169.134.9",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.236.195.8",
                                "target": "1.168.90.211",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.236.195.8",
                                "target": "1.162.137.177",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.231.164.151",
                                "target": "1.172.170.104",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.231.164.151",
                                "target": "111.241.148.140",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "36.231.164.151",
                                "target": "111.248.30.26",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "122.117.25.3",
                                "target": "180.176.111.159",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "122.117.25.3",
                                "target": "122.118.6.123",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "122.117.25.3",
                                "target": "122.118.13.55",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.7.126",
                                "target": "122.146.69.24",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.7.126",
                                "target": "122.121.192.34",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.7.126",
                                "target": "122.121.118.209",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.32.120",
                                "target": "122.121.30.227",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.32.120",
                                "target": "114.26.30.34",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.32.120",
                                "target": "114.45.163.49",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.160.119",
                                "target": "114.45.105.76",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.160.119",
                                "target": "114.44.15.175",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            },
                            {
                                "source": "218.164.160.119",
                                "target": "114.27.45.128",
                                "emphasis": {},
                                "blur": {},
                                "select": {},
                                "ignoreForceLayout": false
                            }
                        ]
                    }
                ],
                "legend": [
                    {
                        "data": [],
                        "selected": {}
                    }
                ],
                "tooltip": {
                    "show": true,
                    "trigger": "item",
                    "triggerOn": "mousemove|click",
                    "axisPointer": {
                        "type": "line"
                    },
                    "showContent": true,
                    "alwaysShowContent": false,
                    "showDelay": 0,
                    "hideDelay": 100,
                    "enterable": false,
                    "confine": false,
                    "appendToBody": false,
                    "transitionDuration": 0.4,
                    "textStyle": {
                        "fontSize": 14
                    },
                    "borderWidth": 0,
                    "padding": 5,
                    "order": "seriesAsc",
                    "formatter": this.formatterHover,
                }
            }
        }
    },
    methods: {
        initChart() {
            this.chart = echarts.init(document.getElementById('13d5a5ac13564d548d0ad1440191544d'), 'white', { renderer: 'canvas' });
            this.chart.setOption(this.option);
        },
        formatterHover(params) {
            if (params.dataType == "node") {
                // var deviceType = params.data.type;
                var imgPath = params.data.symbol;
                var imgPathSrc = imgPath.split("image://")[1];
                return "<img src='" + imgPathSrc + " ' width='30px' height='30px'>" + '<span style="padding: 0 5px;font-size: 14px;">设备类型：' + params.data.device + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">IP：' + params.data.ip + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">geoinfo：' + params.data.geoinfo.country +' '+ params.data.geoinfo.city+'</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">location：'+ '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">lat：' + params.data.location.lat + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">lon：' + params.data.location.lon + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">PoweredBy：' + params.data.PoweredBy + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">organization：' + params.data.organization + '</span>' + '<br>'
                    + '<span style="padding-left:5px;height:30px;line-height:30px;display: inline-block;font-size: 14px;">aso：' + params.data.aso + '</span>' + '<br>'
            //         + '<button style="padding:2px 5px;border:none;outline:none;color:#ffffff;border-radius: 5px;background:rgba(0,0,0,0.5);"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> 路由列表</button>'
            //         + '<button style="padding:2px 5px;border:none;outline:none;color:#ffffff;border-radius: 5px;background:rgba(0,0,0,0.5);margin-left: 10px;"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> ARP列表</button>';
            }
        }
    },
    mounted: function () {
        this.initChart()
    }
}
</script>

<template>
    <div id="13d5a5ac13564d548d0ad1440191544d" class="chart-container" style="width:800px; height:600px; "></div>
</template>

<style scoped>
.chart-container {
    border-style: solid;
}
</style>
