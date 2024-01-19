n, m = map(int, input().split())

def back_all(arr_to_move):
    for i in range(19, 0, -1):
        arr_to_move[i+1] = arr_to_move[i]
    arr_to_move[1] = 0

def front_all(arr_to_move):
    for i in range(1, 21):
        arr_to_move[i-1] = arr_to_move[i]
    arr_to_move[20] = 0

trains = [0] * n
check = set()

for i in range(m):
    size = 2**20
    command = list(map(int, input().split()))

    op = command[0]
    i = command[1] - 1

    if op == 1:
        pos = command[2] - 1
        trains[i] |= (1 << pos)

    elif op == 2:
        pos = command[2] - 1
        trains[i] &= ~(1 << pos)

    elif op == 3:
        trains[i] = (trains[i] << 1) & (size - 1)

    elif op == 4:
        trains[i] = (trains[i] >> 1) & ((size - 1) >> 1)

for train in trains:
    check.add(train)

print(len(check))