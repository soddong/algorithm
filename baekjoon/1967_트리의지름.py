from collections import defaultdict
import heapq

n = int(input())
relations = defaultdict(list)
rootfinder = set(range(1, n+1))
for _ in range(n-1):
    u, v, w = map(int, input().split())
    relations[u].append([v, w])
    relations[v].append([u, w])
    rootfinder.remove(v)
root = list(rootfinder)[0]

def findEndpointAndLength(u):
    checked = set()
    q = []
    max_sum = []
    heapq.heappush(q, [0, u])
    while q:
        sum, now = heapq.heappop(q)
        sum *= -1
        checked.add(now)
        heapq.heappush(max_sum, [(sum)*(-1), now])
        for next, weight in relations[now]:
            if next in checked:
                continue
            heapq.heappush(q, [(sum + weight)*(-1), next])
    return heapq.heappop(max_sum)

def start():
    endpoint = findEndpointAndLength(root)[1]
    return findEndpointAndLength(endpoint)[0]*(-1)
    

print(start())