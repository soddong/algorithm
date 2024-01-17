# 4673 : 셀프 넘버

CHECK_NUMBER = 10000
nums = set(range(1, 10000))

def calculate(num):
    sum_nums = num
    while num > 0:
        sum_nums += num % 10
        num = int(num/10)
    return sum_nums

for num in range(1, CHECK_NUMBER):
    making_num = calculate(num)
    if making_num in nums:
        nums.remove(making_num)

for num in nums:
    print(num)