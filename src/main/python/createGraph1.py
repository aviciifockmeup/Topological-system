import random
import sys
import json


def create_graph(node_num, edge_num):
    node_list = []
    edge_list = []

    for i in range(int(node_num)):
        path1 = random.randint(1, 10)
        path2 = random.randint(0, 10)
        path3 = random.randint(0, 10)
        path4 = random.randint(0, 10)

        node = str(path1) + '.' + str(path2) + '.' + str(path3) + '.' + str(path4)

        node_list.append(node)

    for i in range(int(edge_num)):
        edge = node_list[i] + ',' + node_list[i + 1]
        edge_list.append(edge)

    return {"node_list": node_list, "edge_list": edge_list}


if __name__ == '__main__':
    node_num = sys.argv[1]
    edge_num = sys.argv[2]

    node_list = create_graph(node_num,edge_num)

#     with open('nodes.json', 'w') as json_file:
#         json.dump(node_list, json_file)

    print(node_list)