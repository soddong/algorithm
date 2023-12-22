# 19236

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

board = [[] for _ in range(4)]
fish = [[] for _ in range(16)]
ans = 0

# 1. 입력 받기
for i in range(4):
    temp = list(map(int, input().split()))
    for j in range(0, 7, 2):
        board[i].append((temp[j] - 1, temp[j + 1] - 1))
        fish[temp[j]-1] = [i, j//2]

# 3. 상어 이동 및 물고기 먹기
def dfs_shark(x, y, d, cnt):
    global ans, board, fish
    move_fish(x, y)
    while True:
        nx, ny = x + dx[d], y + dy[d]
        if not 0 <= nx < 4 or not 0 <= ny < 4:
            ans = max(ans, cnt)
            return
        if not board[nx][ny]:
            x, y = nx, ny
            continue

        temp_map = [dim[:] for dim in board]
        temp_fish = [dim[:] for dim in fish]

        fish[board[nx][ny][0]], board[nx][ny] = [], []
        dfs_shark(nx, ny, temp_map[nx][ny][1], cnt + temp_map[nx][ny][0] + 1)
        board, fish = temp_map, temp_fish
        x, y = nx, ny


# 2. 물고기 이동
def move_fish(sx, sy):
    for i in range(16):
        if fish[i]:
            x, y = fish[i][0], fish[i][1]
            ori_d = board[x][y][1]
            d = ori_d
            while True:
                nx, ny = x + dx[d], y + dy[d]
                if not 0 <= nx < 4 or not 0 <= ny < 4 or (sx == nx and sy == ny):
                    d = (d + 1) % 8
                    if d == ori_d:
                        break
                    continue
                if board[nx][ny]:
                    fish[board[nx][ny][0]] = [x, y]
                board[x][y] = board[nx][ny]
                board[nx][ny] = (i, d)
                fish[i] = [nx, ny]
                break


d, cnt = board[0][0][1], board[0][0][0]
fish[cnt], board[0][0] = [], []
dfs_shark(0, 0, d, cnt + 1)
print(ans)