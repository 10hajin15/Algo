import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        Stack<Subject> stack = new Stack<>();
        PriorityQueue<Subject> pq = new PriorityQueue<>();
        
        for(String[] plan: plans) {
            String[] st = plan[1].split(":");
            int it = (Integer.parseInt(st[0]) * 60) + Integer.parseInt(st[1]);
            pq.add(new Subject(plan[0], it, Integer.parseInt(plan[2])));
        }
        
        int time = 0;
        List<String> result = new ArrayList<>();
        
        while(!pq.isEmpty()) {
            Subject now = pq.poll();
            
            while(!stack.isEmpty() && time < now.start) {
                Subject prev = stack.pop();
                
                if(prev.time - (now.start-time) > 0) {
                    stack.add(new Subject(prev.name, now.start, prev.time - (now.start-time)));
                    break;
                }
                
                result.add(prev.name);
                time += prev.time;
            }
            
            if(!pq.isEmpty() && now.start + now.time > pq.peek().start) {
                stack.add(new Subject(now.name, pq.peek().start, now.time - (pq.peek().start - now.start)));
                time = pq.peek().start;
            } else {
                time = now.start + now.time;
                result.add(now.name);
            }
        }
                          
        while(!stack.isEmpty()) {
            Subject sj = stack.pop();
            result.add(sj.name);
        }
        
        String[] answer = new String[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
    
    class Subject implements Comparable<Subject> {
        String name;
        int start, time;
        
        Subject(String name, int start, int time) {
            this.name = name;
            this.start = start;
            this.time = time;
        }
        
        @Override
        public int compareTo(Subject s) {
            return Integer.compare(this.start, s.start);
        }
    }
}