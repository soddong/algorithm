from collections import defaultdict
import heapq
import sys

input = sys.stdin.readline
V, E = map(int, input().split())
K = int(input()) # 시작 정점의 번호

my_map = defaultdict(list)
for _ in range(E):
    u, v, w = map(int, input().split())
    my_map[u].append([v, w])

# start
distance = [float("inf")] * (V+1)

def dijkstra(start):
    global distance
    q = []
    distance[start] = 0
    heapq.heappush(q, [0, start])

    while q:
        total_w, u = heapq.heappop(q)

        if distance[u] < total_w:
            continue

        for v, w in my_map[u]:
            if w + total_w < distance[v]:
                distance[v] = w + total_w
                heapq.heappush(q, [distance[v], v])

def printResult():
    for i, d in enumerate(distance):
        if i == 0:
            continue

        if d == float("inf"):
            print("INF")
        else:
            print(d)

dijkstra(K)
printResult()