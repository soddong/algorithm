from collections import deque
import heapq

V, E, P = map(int, input().split())

loads = [[0] * (V + 1) for _ in range(V + 1)]

for _ in range(E):
    a, b, c = map(int, input().split())
    loads[a][b] = c
    loads[b][a] = c

def dfs(start, end):
    q = []
    checked = [-1] * (V + 1)

    q.append([start, 0])
    checked[start] = 0

    while q:
        now_idx, now_sum = q.pop()
        if checked[now_idx] != -1 and now_sum > checked[now_idx]:
            continue
        for next in range(1, V + 1):
            if loads[now_idx][next] == 0:
                continue
            if checked[next] == -1 or now_sum + loads[now_idx][next] < checked[next]:
                checked[next] = now_sum + loads[now_idx][next]
                q.append([next, checked[next]])
    # print(checked[end])
    return checked[end]

def dijkstra(start, end):
    heap_q = []
    checked = [-1] * (V + 1)

    heapq.heappush(heap_q, [0, start])
    checked[start] = 0

    while heap_q:
        now_sum, now_idx = heapq.heappop(heap_q)
        if checked[now_idx] != -1 and now_sum > checked[now_idx]:
            continue
        for next in range(1, V + 1):
            if loads[now_idx][next] == 0:
                continue
            if checked[next] == -1 or now_sum + loads[now_idx][next] < checked[next]:
                checked[next] = now_sum + loads[now_idx][next]
                heapq.heappush(heap_q, [checked[next], next])

    return checked[end]

result = dijkstra(1, V)
if result == (dijkstra(1, P) + dijkstra(P, V)):
    print("SAVE HIM")
else:
    print("GOOD BYE")
