import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int weight : people) {
            deque.add(weight);
        }

        int boats = 0;
        
        while (!deque.isEmpty()) {
            int a = deque.pollLast();
            
            if (!deque.isEmpty() && a + deque.peekFirst() <= limit) {
                deque.pollFirst();
            }
            
            boats++;
        }
        
        return boats;
    }
}
