# 11656 접미사 배열

s = input()

arr_s = []

for i in range(len(s)):
    arr_s.append(s[-1-i:])

for str in sorted(arr_s):
    print(str)