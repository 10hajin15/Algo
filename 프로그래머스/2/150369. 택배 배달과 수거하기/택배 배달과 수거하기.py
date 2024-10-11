def solution(cap, n, deliveries, pickups):
    total_distance = 0

    deli = 0
    pick = 0

    for i in range(n - 1, -1, -1):
        deli += deliveries[i]
        pick += pickups[i]

        while deli > 0 or pick > 0:
            total_distance += (i + 1) * 2

            deli -= cap
            pick -= cap

    return total_distance