from collections import deque
n, m = map(int, input().split())
field = []
for _ in range(n):
    field.append(list(map(int, input())))
q = deque()
q.append((0, 0))
visited = [[0] * m for _ in range(n)]
visited[0][0] = 1
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
answer = 0
while q:
    x, y = q.popleft()
    if x == (n - 1) and y == (m - 1):
        answer =  visited[x][y]
        break
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        if visited[nx][ny] == 0 and field[nx][ny] == 1:
            q.append((nx, ny))
            visited[nx][ny] = visited[x][y] + 1
print(answer)