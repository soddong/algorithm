import math

n, m = map(int, input().split())
go_days = [1] * (m+1)
prices = [[1, 0, 10000], [3, 1, 25000], [5, 2, 37000]]
for _ in range(m):
    not_day = int(input())
    go_days[not_day] = 0

dp = [[float('inf')] * (n+1) for _ in range(n+1)]

def dfs(day, coupon):
    if day <= n:
        if dp[day][coupon] != float('inf'):
            return dp[day][coupon]
        if go_days[day]:
            dp[day][coupon] = dfs(day+1, coupon)
            return dp[day][coupon]
        for i in range(3):
            dp[day][coupon] = min(dp[day][coupon],
                                  dfs(day + prices[i][0], coupon + prices[i][1]) + prices[i][2])
    return 0
dfs(1, 0)