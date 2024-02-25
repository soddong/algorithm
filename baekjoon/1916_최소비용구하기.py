from collections import defaultdict
import heapq
import sys

input = sys.stdin.readline
n = int(input())
m = int(input())

relation = defaultdict(list)

def dfs(start, end):
    q = []
    dist = [float('inf')] * (n+1)
    heapq.heappush(q, [0, start])
    dist[start] = 0

    while q:
        now_w, now_u = heapq.heappop(q)

        if now_w > dist[now_u]:
            continue

        for v, w in relation[now_u]:
            if dist[v] > w + now_w:
                dist[v] = w + now_w
                heapq.heappush(q, [dist[v], v])

    return dist[end]

for i in range(m):
    u, v, w = map(int, input().split())
    relation[u].append([v, w])

u, v = map(int, input().split())
print(dfs(u, v))