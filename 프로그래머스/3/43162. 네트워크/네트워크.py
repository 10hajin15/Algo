from collections import deque

def solution(n, computers):
    def bfs(start):
        q = deque()
        q.append(start)
        while q:
            now = q.popleft()
            for nxt in graph[now]:
                if visited[nxt]: continue
                visited[nxt] = True
                q.append(nxt)
    
    answer = 0
    
    visited = [False] * n
    graph = [[] for _ in range(n)]
    
    for i in range(n):
        for j in range(n):
            if i==j: continue
            if computers[i][j] == 1:
                graph[i].append(j)
                
    
    for i in range(n):
        if not visited[i]:
            answer += 1
            visited[i] = True
            bfs(i)
    
    
    return answer