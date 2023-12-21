# 16236
# 12:40 ~ 13:15
import heapq
from collections import deque

# TODO: pushDistance와 getDistance를 굳이 나눌 필요가 없음 -> 비효율
# n*n 공간, m마리 물고기, 1마리 아기상어
# 아기상어의 크기는 2

# 요구사항
# [x] 먹을수 있는 고기가 없다면 종료
# [x] 먹을수있는 고기가 1마리면 그거
# [x] 여러마리면 가장 가까운
# [x] 가까운 고기가 많다면 위 -> 왼

# [x] 이동은 1초
# [x] 물고기 먹으면 빈칸, 상어 스택+1
# [x] 상어 스택이 레벨과 같아지면 레벨업

# 1. 입력 받기 & 변수 선언
n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]

shark = [0, 0, 0, 0] # point, fishes, x, y
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
heap = []
answer = 0

def findSharkInitalPos():
    global shark
    for i in range(n):
        for j in range(n):
            if board[i][j] == 9:
                board[i][j] = 0
                shark = [2, 0, i, j]
                return

def getDistance(q, dst_x, dst_y):
    checked = [[0] * n for _ in range(n)]

    while q:
        x, y, cnt = q.popleft()
        if x == dst_x and y == dst_y:
            return cnt
        for d in range(4):
            nx, ny = x + dx[d], y + dy[d]
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            if checked[nx][ny] == 0 and board[nx][ny] <= shark[0]:
                checked[nx][ny] = 1
                q.append([nx, ny, cnt+1])
    return -1

def pushDistance():
    heap = []
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0:
                continue
            if board[i][j] < shark[0]:
                q = deque()
                q.append([shark[2], shark[3], 0])
                cnt = getDistance(q, i, j)
                if cnt != -1:
                    heapq.heappush(heap, [cnt, i, j])
    return heap

def repeatAndGetResult():
    global answer
    while True:
        # 모든 공간을 돌면서, 상어와의 거리 push
        distance_heap = pushDistance()

        # 만약, 갈곳이 없으면 break
        if not distance_heap:
            return answer

        # 갈곳이 있으면 간 후 점수 획득, 그자리는 0
        cnt, x, y = heapq.heappop(distance_heap)
        shark[2:] = [x, y]
        shark[1] += 1
        board[x][y] = 0
        answer += cnt

        # 상어 레벨업
        if shark[1] == shark[0]:
            shark[0] += 1
            shark[1] = 0


# 2. 초기 상어 위치 찾기
findSharkInitalPos()

# 3. 반복
print(repeatAndGetResult())
