import sys
from itertools import combinations
input = sys.stdin.readline
n, m = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]
houses = []
chickens = []
for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            houses.append((i, j))
        elif city[i][j] == 2:
            chickens.append((i, j))
result = int(1e9)
for chicken in combinations(chickens, m):
    total_distance = 0
    for house in houses:
        distance = int(1e9)
        for c in chicken:
            distance = min(distance, abs(house[0] - c[0]) + abs(house[1] - c[1]))
        total_distance += distance
    result = min(result, total_distance)
print(result)