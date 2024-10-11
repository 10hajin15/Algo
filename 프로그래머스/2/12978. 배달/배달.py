import heapq
INF = int(1e9)

def solution(N, road, K):
    answer = 0

    graph = [[] for _ in range(N+1)]

    for r in road:
        a = r[0]
        b = r[1]
        c = r[2]
        graph[a].append((b,c))
        graph[b].append((a,c))

    distance = [INF] * (N+1)

    q = []
    heapq.heappush(q, (0, 1))
    distance[1] = 0
    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist:
            continue
        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

    for i in range(1, N+1):
        if distance[i] == INF: continue
        if distance[i] <= K:
            answer += 1

    return answer