# 13565 : 침투
# RecursionError. 재귀로 했다가 에러 발생 (최대 깊이는 1000)

n, m = map(int, input().split())
info = [list(map(int, input())) for _ in range(n)]
checked = [[0] * m for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

def dfs():
    while q:
        x, y = q.pop()
        checked[x][y] = 1
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if checked[nx][ny] == 1 or info[nx][ny] == 1:
                continue
            q.append([nx, ny])

# start
q = []
for j in range(m):
    if info[0][j] == 1:
        continue
    q.append([0, j])
    dfs()

cnt = 0
for j in range(m):
    if checked[n-1][j] == 1:
        print("YES")
        break
else:
    print("NO")