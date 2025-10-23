import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int n = food_times.length;
        
        PriorityQueue<Food> pq = new PriorityQueue<>();
        
        long sum = 0;
        for(int i=0; i<food_times.length; i++) {
            long time = food_times[i];
            sum += time;
            pq.add(new Food(time, i+1));
        }
        
        if(sum <= k) return -1;
        
        long prev = 0;
        long sumTime = 0;
        long length = n;
        while(!pq.isEmpty()) {
            Food now = pq.peek();
            long add = (now.time - prev) * length;
            
            if(sumTime + add > k) break;
            
            sumTime += add;
            length--;
            pq.poll();
            prev = now.time;
        }
        
        List<Food> remain = new ArrayList<>();
        while(!pq.isEmpty()) remain.add(pq.poll());
        
        Collections.sort(remain, (a, b) -> Integer.compare(a.n, b.n));
        
        long idx = (k-sumTime) % length;
        
        return remain.get((int)idx).n;
    }
    
    class Food implements Comparable<Food> {
        long time;
        int n;
        
        Food(long time, int n) {
            this.time = time;
            this.n = n;
        }
        
        @Override
        public int compareTo(Food f) {
            return Long.compare(this.time, f.time);
        }
    }
}