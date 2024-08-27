import sys
import copy
from itertools import combinations
input = sys.stdin.readline

def attack(archer):
    attack_list = list()
    cnt = 0
    for ar in archer:
        pos = list()
        for i in range(n):
            for j in range(m):
                if temp[i][j] == 1:
                    now_d = abs(n - i) + abs(ar - j)
                    if now_d <= d:
                        pos.append((now_d, i, j))
        pos.sort(key=lambda x: (x[0], x[2]))
        if pos: attack_list.append(pos[0])

    for enemy in attack_list:
        _, r, c = enemy
        if temp[r][c] == 1:
            temp[r][c] = 0
            cnt += 1

    return cnt

def move_down():
    for i in range(-1, -n, -1):
        temp[i] = temp[i - 1]
    temp[0] = [0 for _ in range(m)]


def is_empty():
    for i in range(n):
        for j in range(m):
            if temp[i][j] == 1:
                return False
    return True

n, m, d = map(int, input().split())
field = [list(map(int, input().split())) for _ in range(n)]
archers = [i for i in range(m)]

result = 0
for archer in combinations(archers, 3):
    temp = copy.deepcopy(field)
    count = 0
    while not is_empty():
        count += attack(archer)
        move_down()
    result = max(result, count)

print(result)