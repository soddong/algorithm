answer = 0
 
def inputAB():
    global A, B
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
 
 
def start(small, big):
    s_n, b_n = len(small), len(big)  # 2 5 -> 3
    max_val = 0
    for i in range(b_n - s_n + 1):
        sum_val = 0
        j_s = 0
        for j_b in range(i, i + s_n):
            sum_val += small[j_s] * big[j_b]
            j_s += 1
        max_val = max(max_val, sum_val)
    return max_val
 
 
T = int(input())

for test_case in range(1, T + 1):
    n, m = map(int, input().split())
 
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    if n < m:
        answer = start(A, B)
    else:
        answer = start(B, A)
 
    print('#{0} {1}'.format(test_case, answer))
