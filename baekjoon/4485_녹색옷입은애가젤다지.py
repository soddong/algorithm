# 0, 0 -> n-1, n-1 까지의 최소 비용
import heapq
n = 0
arr = [[0] for _ in range(n)]
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
result = -1

def dfs():
    global n, result
    q = []
    dist = [[float("inf")]*n for _ in range(n)]
    heapq.heappush(q, [arr[0][0], 0, 0]) # 비용, x, y
    dist[0][0] = arr[0][0]

    while q:
        w, x, y = heapq.heappop(q)

        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if dist[nx][ny] > w + arr[nx][ny]:
                dist[nx][ny] = w + arr[nx][ny]
                heapq.heappush(q, [w + arr[nx][ny], nx, ny])

    return dist[n-1][n-1]

tc = 1
while True:
    n = int(input())
    if n == 0:
        break
    arr = [list(map(int, input().split())) for _ in range(n)]
    print("Problem %d: %d" %(tc, dfs()))
    tc+=1