import heapq

N = int(input())
hq = []

for i in range(N):
    s, e = map(int, input().split())
    heapq.heappush(hq, (e, s))

count = 0
end_time = 0

while hq:
    e, s = heapq.heappop(hq)
    if s >= end_time:
        count += 1
        end_time = e

print(count)