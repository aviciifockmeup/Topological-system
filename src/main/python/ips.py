# 导入random模块
import random


# 定义一个函数，用于生成num个随机IP地址，并返回一个列表
def create_ips_list(num):
    # 定义一个空列表，用于存储IP地址
    ips_list = []
    # 循环num次
    for i in range(num):
        # 生成四个随机整数，范围是0到255，作为IP地址的四个段
        ip_segment1 = random.randint(0, 255)
        ip_segment2 = random.randint(0, 255)
        ip_segment3 = random.randint(0, 255)
        ip_segment4 = random.randint(0, 255)
        # 将四个段用点号连接起来，形成一个IP地址字符串
        ip_address = str(ip_segment1) + "." + str(ip_segment2) + "." + str(ip_segment3) + "." + str(ip_segment4)
        # 将IP地址字符串添加到列表中
        ips_list.append(ip_address)
    # 返回IP地址列表
    return ips_list


# 定义一个函数，用于从IP地址列表中随机选取30个不重复的IP地址，形成一个新的列表，并根据这个列表生成一个字典，包含节点列表和边列表
def create_traceroute_path():
    # 调用create_ips_list函数，生成一个包含100个IP地址的列表
    ips_list = create_ips_list(100)
    # 定义一个空列表，用于存储选取的IP地址
    selected_ips_list = []
    # 循环30次
    for i in range(30):
        # 从IP地址列表中随机选取一个IP地址
        ip_address = random.choice(ips_list)
        # 如果这个IP地址已经在选取的列表中，就跳过这次循环，重新选取
        if ip_address in selected_ips_list:
            continue
        # 否则，将这个IP地址添加到选取的列表中
        else:
            selected_ips_list.append(ip_address)
    # 定义一个空字典，用于存储节点列表和边列表
    traceroute_dict = {}
    # 将选取的IP地址列表作为节点列表，赋值给字典的node_list键
    traceroute_dict["node_list"] = selected_ips_list
    # 定义一个空列表，用于存储边列表
    edge_list = []
    # 循环选取的IP地址列表的长度减一次
    for i in range(len(selected_ips_list) - 1):
        # 将当前的IP地址和下一个IP地址用逗号连接起来，形成一个边字符串
        edge = selected_ips_list[i] + "," + selected_ips_list[i + 1]
        # 将边字符串添加到边列表中
        edge_list.append(edge)
    # 将边列表作为边列表，赋值给字典的edge_list键
    traceroute_dict["edge_list"] = edge_list
    # 返回字典
    return traceroute_dict


# 定义一个函数，用于循环调用create_traceroute_path函数，生成num个字典，并返回一个列表
def create_paths(num):
    # 定义一个空列表，用于存储字典
    paths_list = []
    # 循环num次
    for i in range(num):
        # 调用create_traceroute_path函数，生成一个字典
        traceroute_dict = create_traceroute_path()
        # 将字典添加到列表中
        paths_list.append(traceroute_dict)
    # 返回列表
    return paths_list


def merge_dicts(dict_list):
    merged_nodes = set()
    merged_edges = set()

    for d in dict_list:
        merged_nodes.update(d['node_list'])
        merged_edges.update(d['edge_list'])

    merged_dict = {"node_list": list(merged_nodes), "edge_list": list(merged_edges)}
    return merged_dict


if __name__ == '__main__':
    path_list = []
    path_list = create_paths(4)
    merged_ips = merge_dicts(path_list)
    print(merged_ips)
    # print(len(merged_ips["node_list"]))
    # print(len(merged_ips["edge_list"]))
