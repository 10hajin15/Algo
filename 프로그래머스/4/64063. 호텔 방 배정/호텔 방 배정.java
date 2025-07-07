import java.util.*;

class Solution {
    Map<Long, Long> map = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = find(room_number[i]);
        }

        return answer;
    }

    private long find(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }

        long next = find(map.get(room));
        map.put(room, next);
        return next;
    }
}
