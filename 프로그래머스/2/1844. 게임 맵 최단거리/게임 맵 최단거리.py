from collections import deque

dx = [-1,0,1,0]
dy = [0,-1,0,1]

def solution(maps):
    n = len(maps)
    m = len(maps[0])

    visited = [[-1] * m for _ in range(n)]
    visited[0][0] = 1
    q = deque()
    q.append((0,0))
    while q:
        di, dj = q.popleft()

        for i in range(4):
            ni = di+dx[i]
            nj = dj+dy[i]
            if ni < 0 or ni >= n or nj < 0 or nj >= m:
                continue
            if visited[ni][nj] != -1:
                continue
            if maps[ni][nj] == 0: continue
            visited[ni][nj] = visited[di][dj] + 1
            q.append((ni, nj))

    return visited[n-1][m-1]