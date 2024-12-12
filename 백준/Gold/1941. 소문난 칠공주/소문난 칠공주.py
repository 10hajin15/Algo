from collections import deque

def bound(lst):
    vtmp = [[1]*5 for _ in range(5)]
    for i, j in lst:
        vtmp[i][j] = 0
    
    q = deque()
    q.append(lst[0])
    while q:
        si, sj = q.popleft()
        vtmp[si][sj] = 1
        for di, dj in ((-1,0),(1,0),(0,-1),(0,1)):
            ni,nj = si+di, sj+dj
            if 0<=ni<5 and 0<=nj<5 and not vtmp[ni][nj]:
                q.append((ni,nj))
    
    for i in range(5):
        for j in range(5):
            if vtmp[i][j] == 0:
                return False

    return True

def count(lst):
    y = 0
    for i, j in lst:
        if arr[i][j] == 'Y':
            y += 1
            if y >= 4:
                return False
    return True

def dfs(n, start, lst):
    global cnt
    if n == 7:
        if count(lst) and bound(lst):
            cnt += 1
        return
    for i in range(start, 25):
        if not v[i]:
            v[i] = 1
            dfs(n+1, i+1, lst+[ij[i]])
            v[i] = 0

arr = [list(input()) for _ in range(5)]
ij = [(i,j) for i in range(5) for j in range(5)]
v = [0]*25
cnt = 0
dfs(0, 0, [])

print(cnt)