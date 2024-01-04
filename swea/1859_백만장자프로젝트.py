def calculateProfit(n, prices):
    answer = 0
    
    max_price = prices[-1]
    for i in range(len(prices)-1, -1, -1):
        if prices[i] < max_price:
            answer += max_price - prices[i]
        if prices[i] > max_price:
            max_price = prices[i]
    return answer
    
T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    price_per_day = list(map(int, input().split()))

    print('#{0} {1}'.format(test_case, calculateProfit(n, price_per_day)))