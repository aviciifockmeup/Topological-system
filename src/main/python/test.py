"""
    输入： 本地同目录json文件
    输入格式：  {
                "node_list" : ["1.1.0.1","2.3.4.5","3.0.0.1"],
                "edge_list" : ["1.1.0.1,2.3.4.5","2.3.4.5,3.0.0.1"]
            }
    输出： 在本地同目录新建json文件并存入关键节点信息（本例子为度中心信息）
    输出格式： 
            {"1.1.0.1": 0.5, "2.3.4.5": 1.0, "3.0.0.1": 0.5}
}
"""




import networkx as nx
import json  # 导入json模块，用于解析JSON参数
import sys

# 创建一个空的无向图
G = nx.Graph()

def buildGraph(json_Path): 
    with open(json_Path, 'r',encoding='utf-8') as f:
        data = json.load(f)

    # 添加节点
    for node in data["node_list"]:
        G.add_node(node)
    # 添加边    
    for edge in data["edge_list"]: #edge: "1,2"
        a, b = edge.split(',')
        G.add_edge(a, b)

def calculate():
    # 计算节点的度中心性
    degree_centrality = nx.degree_centrality(G)

    # 打印节点的度中心性
    dict = {}
    for node, centrality in degree_centrality.items():
        dict[node] = centrality
        # print(f"节点 {node} 的度中心性：{centrality}")
    with open("Degree.json",'w',encoding='utf-8') as f2:
        json.dump(dict,f2,ensure_ascii=False)

    return dict


if __name__ == "__main__":
    json_Path = sys.argv[1]
    buildGraph(json_Path)
    print(calculate())

