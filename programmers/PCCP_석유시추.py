# 23:30 ~ 23:50

def solution(land):
    answer = 0
    n, m = len(land), len(land[0])
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    
    points_by_line = [0] * m
    
    def dfs():
        q = []
        checked = [[0] * m for _ in range(n)]
        for i in range(n):
            for j in range(m):
                if checked[i][j] == 1 or land[i][j] == 0:
                    continue
                columns_in_unit = set()
                
                cnt = 1
                checked[i][j] = 1
                q.append([i, j])
                columns_in_unit.add(j)
                
                while q:
                    x, y = q.pop()
                    for d in range(4):
                        nx, ny = x + dx[d], y + dy[d]
                        if nx < 0 or nx >= n or ny < 0 or ny >= m:
                            continue
                        if checked[nx][ny] == 1 or land[nx][ny] == 0:
                            continue   
                        cnt += 1
                        checked[nx][ny] = 1
                        q.append([nx, ny])
                        columns_in_unit.add(ny)
                
                for column in columns_in_unit:
                    points_by_line[column] += cnt
    
    dfs()
    answer = max(points_by_line)
    
    return answer