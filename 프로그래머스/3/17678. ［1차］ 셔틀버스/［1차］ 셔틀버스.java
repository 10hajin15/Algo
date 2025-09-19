import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String time: timetable) {
            int hour = Integer.parseInt(time.split(":")[0]) * 60;
            int minute = Integer.parseInt(time.split(":")[1]);
            
            pq.add(hour+minute);
        }
 
        String answer = "";
        int time = 9 * 60;
        for(int i=0; i<n; i++) {
            Deque<Integer> dq = new LinkedList<>();
            
            for(int j=0; j<m; j++) {
                if(pq.isEmpty()) break;
                if(pq.peek() > time) break;
                dq.add(pq.poll());
            }
            
            if(i == n-1) {
                if(dq.size() >= m) {
                    int tmp = dq.pollLast()-1;
                    answer = String.format("%02d:%02d", tmp/60, tmp%60);
                    break;
                } else {
                    answer = String.format("%02d:%02d", time/60, time%60);
                }
            }
            
            time += t;
        }
        
        return answer;
    }
}