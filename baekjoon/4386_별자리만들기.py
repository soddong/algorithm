import heapq
import math

answer = 0
n = int(input())
stars = [list(map(float, input().split())) for _ in range(n)]
visited = set()
q = []

dist = [[0] * n for _ in range(n)]

for i in range(n):
    for j in range(n):
        if i == j:
            continue
        dist[i][j] = math.sqrt(math.pow(stars[i][0] - stars[j][0], 2) + math.pow(stars[i][1] - stars[j][1], 2))

heapq.heappush(q, [0, 0])
while q:
    sum, now = heapq.heappop(q)
    if now in visited:
        continue
    visited.add(now)
    answer += sum

    for next in range(n):
        if next == now:
            continue
        if next in visited:
            continue
        heapq.heappush(q, [dist[now][next], next])

print(f"{answer:.2f}")