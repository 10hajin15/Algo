n = int(input())
m = int(input())
INF = int(1e9)

dist = [[INF] * n for _ in range(n)]

for i in range(n):
    dist[i][i] = 0

for _ in range(m):
    v, w, cost = map(int, input().split())
    dist[v-1][w-1] = min(dist[v-1][w-1], cost)

for k in range(n):
    for i in range(n):
        for j in range(n):
            if dist[i][k] + dist[k][j] < dist[i][j]:
                dist[i][j] = dist[i][k] + dist[k][j]

for row in dist:
    print(*(0 if val == INF else val for val in row))