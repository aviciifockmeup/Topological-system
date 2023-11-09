import sys
import json




if __name__ == "__main__":

    json_path = sys.argv[1]

    with open(json_path, "r", encoding="utf_8") as f:
        cotent = json.load(f)

    print(cotent)

