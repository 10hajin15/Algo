answer = 0

def dfs(n, num,  numbers, target):
    global answer
    if n == len(numbers):
        if num == target:
            answer += 1
        return
    dfs(n+1, num+numbers[n], numbers, target)
    dfs(n+1, num-numbers[n], numbers, target)


def solution(numbers, target):
    dfs(0, 0,  numbers, target)
    return answer