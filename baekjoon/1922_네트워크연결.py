# prim algorithm
import heapq
import sys

input = sys.stdin.readline

n = int(input())
m = int(input())

arr = [[0] * (n+1) for _ in range(n+1)]
q = []
for _ in range(m):
    u, v, w = map(int, input().split())
    arr[u][v] = w
    arr[v][u] = w

def prim():
    cnt = 0
    result = 0
    checked = [0] * (n+1)
    while q:
        w, now = heapq.heappop(q)
        if checked[now] == 1:
            continue
        checked[now] = 1
        cnt += 1
        result += w

        if cnt == n:
            break
        for next in range(1, n+1):
            if arr[now][next] == 0:
                continue
            if checked[next] == 1:
                continue
            heapq.heappush(q, [arr[now][next], next])
    return result

# w, start
heapq.heappush(q, [0, 1])
print(prim())
