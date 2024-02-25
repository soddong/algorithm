import heapq
import sys

answer = 0
n, m = map(int, input().split())
checked = set()
root = [[0] * (n + 1) for _ in range(n + 1)]
start, end = 0, 0
for _ in range(m):
    u, v, w = map(int, input().split())
    root[u][v] = w
    root[v][u] = w

q = []

def dijkstra():
    global answer, max_w

    while q:
        sum, u = heapq.heappop(q)
        if sum != 0:
            checked.add(u)
        answer += sum

        for v in range(n):
            if root[u][v] == 0:
                continue
            if v in checked:
                continue
            heapq.heappush(q, [w, v])


heapq.heappush(q, [0, 1])
dijkstra()

print(answer)