#
# n = int(input())
#
# arr = [""] * n
#
# def dfs(n):
#     if n == 0:
#         return "moo"
#     return dfs(n-1) + "m" + "o"*(n+2) + dfs(n-1)
#
# arr
# for i in range(1, n):
#     arr[i] = arr[i-1] + "m" + "o"*(i+2) + arr[i-1]
#
# print(dfs(n)[n-1])

def find_moo_char(N):
    # 현재 수열의 길이를 계산
    length = 3
    while N > length:
        length = length * 2 + (length + 1)

    # 수열이 m o o로 시작하므로, N이 해당 구간에 속할 때는 'm' 반환
    if N <= 3:
        return 'm'

    # 현재 수열의 중간 지점을 찾음
    mid = (length - 1) // 2

    # N이 중간 지점보다 작으면 이전 구간에 속하므로 재귀 호출
    if N <= mid:
        return find_moo_char(N)

    # N이 중간 지점과 현재 길이 사이에 속할 때는 'o' 반환
    if N == mid + 1:
        return 'o'

    # N이 중간 지점보다 크면 이후 구간에 속하므로 N을 조정하고 재귀 호출
    return find_moo_char(N - mid - 2)

# 입력 받기
N = int(input())

# N번째 글자 출력
result = find_moo_char(N)
print(result)
