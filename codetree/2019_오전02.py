from collections import defaultdict, deque
from copy import deepcopy
# 시간 복잡도 : O(N * K * M) = 100 * 100 * 100 * 100

# 채취
# 1. 첫번째 열부터 차례때로 탐색
# 2. 위에서 아래로 탐색
# 3. 제일 빨리 발견한 곰팡이 채취

# 이동
# 1. 곰팡이마다 본인의 방향, 속도 만큼 이동
# 2. 벽을 만나면 방향 바꿈
# 3. 이미 곰팡이가 있으면 작은놈 삭제

# 입력
n, m, k = map(int, input().split())
board = [[0] * (m+1) for _ in range(n+1)]
info = defaultdict(list)
dx = [-1, 1, 0, 0] # 1 위, 2 아래, 3 오른쪽, 4 왼쪽
dy = [0, 0, 1, -1]

ans = 0
def init():
    for i in range(1, n+1):
        for j in range(1, m+1):
            board[i][j] = -1

def isRange(x, y):
    return 1 <= x <= n and 1 <= y <= m

def change_dir(d):
    if d == 0:
        return 1
    elif d == 1:
        return 0
    elif d == 2:
        return 3
    elif d == 3:
        return 2
    return -1

# 시간 복잡도 : O(N)
def collect(col):
    global ans, board
    for i in range(1, n+1):
        if board[i][col] != -1:
            num = board[i][col]
            ans += info[num][0][4]
            info[num][0] = [-1, -1, -1, -1, -1]
            board[i][col] = -1
            return

# 시간 복잡도 : O(K * N)
def move():
    global board
    new_board = [[-1] * (m+1) for _ in range(n+1)]
    for v in range(k):
        # x, y, 이동거리, 이동방향, 크기
        v_info = info[v][0]
        if v_info[0] == -1:
            continue

        x, y, dist, d = v_info[0], v_info[1], v_info[2], v_info[3]

        if d == 0 or d == 1:
            dist %= 2 * n - 2
        else:
            dist %= 2 * m - 2

        while dist >= 0:
            if dist == 0:
                # 본인이면 패스
                if new_board[x][y] == v:
                    continue
                # 다른놈이면 크기 비교후 작은놈 제거
                if new_board[x][y] != -1:
                    o_v = new_board[x][y]
                    if info[o_v][0][4] < v_info[4]:
                        info[o_v][0] = [-1, -1, -1, -1, -1]
                        new_board[x][y] = v
                        info[v][0] = [x, y, v_info[2], d, v_info[4]]
                    else:
                        info[v][0] = [-1, -1, -1, -1, -1]
                        new_board[x][y] = o_v
                else:
                    new_board[x][y] = v
                    info[v][0] = [x, y, v_info[2], d, v_info[4]]
                break
            nx, ny = x + dx[d], y + dy[d]
            if not isRange(nx, ny):
                d = change_dir(d)
                nx, ny = x + dx[d], y + dy[d]
            x, y, dist, d = nx, ny, dist-1, d

    board = deepcopy(new_board)


init()

for i in range(k):
    x, y, s, d, b = map(int, input().split())
    board[x][y] = i
    info[i].append([x, y, s, d-1, b]) # x, y, 이동거리, 이동방향, 크기

# 1열부터 차례대로 수집
for j in range(1, m+1):
    collect(j)
    move()

print(ans)