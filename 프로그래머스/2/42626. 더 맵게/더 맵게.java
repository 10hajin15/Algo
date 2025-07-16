import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        int cnt = 0;
        while(pq.peek() < K) {
            if(pq.size() < 2) {
                cnt = -1;
                break;
            }
            
            int m1 = pq.poll();           
            int m2 = pq.poll();
            
            pq.add(m1+(m2*2));
            
            cnt++;
        }
        
        
        return cnt;
    }
}