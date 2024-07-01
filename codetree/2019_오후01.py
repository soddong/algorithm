# 시간 복잡도 : O(N^3 * logN) = 100 * 100 * 100 * log100

# 1) 행의 개수 >= 열의 개수
# 모든 행 정렬 (빈도수 오름차순 -> 숫자 오름차순)
# 빈도수를 수옆에 끼워넣기 (가장 큰것기준이고 0 끼워넣기)

# 2) 행의수 < 열의수
# 모든 열 정렬 (빈도수 오름차순 -> 숫자 오름차순)
# 빈도수를 옆에 끼워넣기

# 행, 열의 길이가 100을 넘어가면 자르기
from collections import defaultdict
from copy import deepcopy

ENABLE_DEBUG = False
###################################################
row_length = 3
col_length = 3
MAX_BOARD = 100

board = [[0] * 100 for _ in range(MAX_BOARD)]
col = [defaultdict(int)] * col_length

def deep_copy(c):
    for i in range(MAX_BOARD):
        for j in range(MAX_BOARD):
            board[i][j] = c[i][j]

# 시간 복잡도 : O(N * N * logN)
def move_row():
    global row_length, board
    new_board = [[0] * MAX_BOARD for _ in range(MAX_BOARD)]
    lengths = [0] * MAX_BOARD

    if ENABLE_DEBUG:
        print("move row")

    for i in range(MAX_BOARD):
        row = defaultdict(int)
        # 1. 빈도수 체크
        for j in range(MAX_BOARD):
            num = board[i][j]
            if num == 0 or num == -1:
                continue
            row[num] += 1

        # 2. 정렬 (value -> key)
        sorted_items = sorted(row.items(), key=lambda x: (x[1], x[0]))

        # 3. 빈도수 끼워넣기
        for j, v in enumerate(sorted_items):
            if j == 50:
                break
            new_board[i][2*j] = v[0]
            new_board[i][2*j + 1] = v[1]

        # 5. row_length 업데이트
        lengths[i] = 2*len(sorted_items)
        row_length = max(row_length, lengths[i])

    for i in range(MAX_BOARD):
        if lengths[i] < row_length:
            for j in range(lengths[i], MAX_BOARD):
                new_board[i][j] = 0


    deep_copy(new_board)

def move_col():
    if ENABLE_DEBUG:
        print("move col")
    global col_length, board
    new_board = [[0] * MAX_BOARD for _ in range(MAX_BOARD)]
    lengths = [0] * MAX_BOARD

    for j in range(MAX_BOARD):
        col = defaultdict(int)
        # 1. 빈도수 체크
        for i in range(MAX_BOARD):
            num = board[i][j]
            if num == 0 or num == -1:
                continue
            col[num] += 1

        # 2. 정렬 (value -> key)
        sorted_items = sorted(col.items(), key=lambda x: (x[1], x[0]))
        # 3. 빈도수 끼워넣기
        for i, v in enumerate(sorted_items):
            if j == 50:
                break
            new_board[2*i][j] = v[0]
            new_board[2*i + 1][j] = v[1]

        # 5. col_length 업데이트
        lengths[j] = 2*len(sorted_items)
        col_length = max(col_length, lengths[j])

    for j in range(MAX_BOARD):
        if lengths[j] < col_length:
            for i in range(lengths[j], 100):
                board[i][j] = 0

    deep_copy(new_board)

r, c, k = map(int, input().split())

board = [[0] * MAX_BOARD for _ in range(MAX_BOARD)]
for i in range(3):
    tmp = list(map(int, input().split()))
    for j in range(3):
        board[i][j] = tmp[j]

if board[r - 1][c - 1] == k:
    print(0)
else:
    for t in range(1, 101):
        if col_length >= row_length:
            move_row()
        else:
            move_col()
        if board[r-1][c-1] == k:
            print(t)
            break
    else:
        print(-1)