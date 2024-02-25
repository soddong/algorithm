from collections import defaultdict
#  O(n * m * (V + E))
answer = ''
n = int(input())

relation = defaultdict(list)
checked = defaultdict(int)

for _ in range(n):
    sentence = input()
    src, dst = sentence[0], sentence[-1]
    relation[src].append(dst)
    checked[dst] += 1

def dfs(src, dst):
    global answer
    if src == dst:
        answer = 'T'
    for x in relation[src]:
        if checked[x] > 0:
            checked[x] -= 1
            dfs(x, dst)
            checked[x] += 1


m = int(input())

for _ in range(m):
    sentence = input()
    answer = 'F'
    dfs(sentence[0], sentence[-1])
    print(answer)