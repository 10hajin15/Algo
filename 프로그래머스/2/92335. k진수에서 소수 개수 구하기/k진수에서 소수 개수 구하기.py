def isPrime(n):
    if n == 1: return False
    
    for i in range(2, int(n**0.5)+1):
        if n % i == 0:
            return False
    return True

def solution(n, k):
    number = ''
    while n:
        div = n % k
        number = str(div) + number
        n //= k
    
    lst = number.split('0')
    answer = 0
    print(lst)
    for st in lst:
        if not st: continue
        if isPrime(int(st)):
            answer += 1
    
    return answer