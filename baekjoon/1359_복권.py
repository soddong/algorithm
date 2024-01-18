from itertools import combinations

N, M, K = map(int,input().split())

answer = 0
totals = list(combinations(range(1, N + 1), M))

for user in totals:
    for computer in totals:
        if len(set(user) & set(computer)) >= K:
            answer += 1

print(answer / (len(totals)*len(totals)))