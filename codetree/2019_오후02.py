from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def printResult(dist):
    global n, board
    ans = -1
    for i in range(n):
        for j in range(n):
            if board[i][j] == 0 and dist[i][j] > ans:
                ans = dist[i][j]
            elif board[i][j] == 0 and dist[i][j] == -1:
                return float('inf')
    return ans


def bfs(selected_hospitals):
    q = deque()
    dist = [[-1] * n for _ in range(n)]

    for hospital in selected_hospitals:
        q.append(hospital)
        dist[hospital[0]][hospital[1]] = 0

    while q:
        x, y = q.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0 <= nx < n and 0 <= ny < n and dist[nx][ny] == -1 and (board[nx][ny] == 0 or board[nx][ny] == 2):
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))

    return printResult(dist)


def backtrack(start, count):
    global ans
    if count == m:
        result = bfs(selected_hospitals)
        ans = min(ans, result)
        return

    for i in range(start, len(hospitals)):
        selected_hospitals.append(hospitals[i])
        backtrack(i + 1, count + 1)
        selected_hospitals.pop()


n, m = map(int, input().split())
board = []
hospitals = []
virus_cnt = 0

for i in range(n):
    row = list(map(int, input().split()))
    board.append(row)
    for j in range(n):
        if row[j] == 2:
            hospitals.append((i, j))
        elif row[j] == 0:
            virus_cnt += 1

if virus_cnt == 0:
    print(0)
else:
    selected_hospitals = []
    ans = float('inf')
    backtrack(0, 0)
    print(-1 if ans == float('inf') else ans)
