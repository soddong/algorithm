now_time = list(map(int, input().split(":")))
after_time = list(map(int, input().split(":")))

wait_time = [0] * 3
after_time[0] += 24

after_seconds = after_time[0] * 60 * 60 + after_time[1] * 60 + after_time[2]
now_seconds = now_time[0] * 60 * 60 + now_time[1] * 60 + now_time[2]

wait_seconds = after_seconds - now_seconds

wait_time[0] = (wait_seconds // (60 * 60)) % 24
wait_time[1] = (wait_seconds % (60 * 60)) // 60
wait_time[2] = wait_seconds % 60
answer = '{:02}:{:02}:{:02}'.format(wait_time[0], wait_time[1], wait_time[2])

if answer == '00:00:00':
    answer = '24:00:00'
print(answer)