def solution(wallet, bill):
    left = min(wallet[0], wallet[1])
    right = max(wallet[0], wallet[1])
    
    bill_left = min(bill[0], bill[1])
    bill_right = max(bill[0], bill[1])
    
    answer = 0
    while True:
        if bill_left <= left and bill_right <= right:
            break
        
        tmp_left = bill_left
        tmp_right = bill_right // 2
        
        bill_left = min(tmp_right, tmp_left)
        bill_right = max(tmp_right, tmp_left)
        answer += 1
    
    return answer