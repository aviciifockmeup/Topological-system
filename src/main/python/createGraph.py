import random
import sys
import json


def create_nodes(node_num):
    node_list = []

    for i in range(int(node_num)):
        path1 = random.randint(1, 10)
        path2 = random.randint(0, 10)
        path3 = random.randint(0, 10)
        path4 = random.randint(0, 10)

        node = str(path1) + '.' + str(path2) + '.' + str(path3) + '.' + str(path4)

        node_list.append(node)
    return node_list


if __name__ == '__main__':
    node_num = sys.argv[1]
#     edge_num = sys.argv[2]

#     print(sys.argv[0])

    node_list = create_nodes(node_num)

    print(node_list)


