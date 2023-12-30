# 22:00 ~ 22:35
# 출력) 남은 체력 (죽으면 -1)

# 요구사항
#   1. 붕대 감기
#       * 1초당 x만큼 체력회복
#       * 연속 성공시 y만큼 체력회복
#   2. 기술 쓰기
#       * 기술 쓰다가 몬스터에게 공격시, 기술취소
#       * 기술취소 or 기술이 끝나면 붕대 감기
#   3. 몬스터에게 공격
#       * 피해량만큼 체력 감소
#       * 체력 0이하시 캐릭터 죽음 -> 게임 끝

def solution(bandage, health, attacks):
    answer = -1
    n = attacks[-1][0]
    time_stamp = [0] * (n+1)
    bandage_cnt = 0
    now_health = health

    def initialize():
        nonlocal time_stamp
        for attack in attacks:
            time_stamp[attack[0]] = attack[1]
            
    def isAttack(l_time):
        if time_stamp[l_time] != 0:
            return True
        return False
    
    def updateHealthFromAttack(l_time, l_health):
        l_health -= time_stamp[l_time]
        if l_health <= 0:
            return -1
        return l_health
    
    def updateHealthFromBandage(l_bandage_cnt, l_health):
        l_health += bandage[1]
        if l_bandage_cnt == bandage[0]:
            l_bandage_cnt = 0
            l_health += bandage[2]
        return l_health, l_bandage_cnt
    
    initialize()
        
    for t in range(1, n+1):
        # 몬스터 공격 -> 체력 업데이트
        is_attack = isAttack(t)
        if is_attack:
            now_health = updateHealthFromAttack(t, now_health)
        if now_health == -1:
            return answer
        
        # 공격 x면 붕대 감기 -> 체력 업데이트
        if is_attack:
            bandage_cnt = 0
        if not is_attack:
            bandage_cnt += 1
            now_health, bandage_cnt = updateHealthFromBandage(bandage_cnt, now_health)
        
        # 체력 조정
        if now_health > health:
            now_health = health
        
    return now_health