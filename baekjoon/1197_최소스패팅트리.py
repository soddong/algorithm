import sys

input = sys.stdin.readline

v, e = map(int, input().split())
tree = []
parents = [i for i in range(0, v+1)]
result = 0

for _ in range(e):
    tree.append(list(map(int, input().split())))

def find(node):
    if node == parents[node]:
        return node
    parents[node] = find(parents[node])
    return parents[node]

def union(a, b):
    roota = find(a)
    rootb = find(b)
    if roota == rootb:
        return
    now = a
    while True:
        next = parents[now]
        if now == parents[now]:
            parents[now] = rootb
            return
        parents[now] = rootb
        now = next


tree.sort(key=lambda x:x[2])
for a, b, c in tree:
    if find(a) != find(b):
        union(a, b)
        result += c
print(result)