# 최대 생명력, 현재 생명력, 공격력
# 방에는 포션이 있거나, 몬스터가 있음
#   몬스터가 있다면 쓰러뜨려야 다음방 이동 가능
#   N번째에는 공주와 용이 있음
#   용을 무찌르면 공주를 구할 수 있음

# 포션이 있는 방 - 생명력 회복, 공격력 증가

n, init_h = map(int, input().split())

room_info = [list(map(int, input().split())) for _ in range(n)]

# 케이스, 공격력, 생명력
for t, a, h in room_info[::-1]:
    # 몬스터
    if t == 1:
        while h > 0:


    # 포션
    if t == 2: