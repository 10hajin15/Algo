import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {        
        int maxHealth = health;
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
            if(a[0] > b[0]) return 1;
            else return -1;
        });
        
        int maxTime = 0;
        for(int i=0; i<attacks.length; i++) {
            pq.offer(attacks[i]);
            maxTime = Math.max(attacks[i][0], maxTime);
        }
        
        int cnt = 0;
        for(int i=1; i<=maxTime; i++) {
            int[] now = pq.peek();
            
            if(now[0] == i) {
                cnt = 0;
                int [] attack = pq.poll();
                health -= attack[1];
                if(health <= 0) break; 
            } else {
                cnt++;
                if(cnt == t) {
                    health += y;
                    cnt = 0;
                }
                health += x;
                
                if(health > maxHealth) health = maxHealth;
            }
        }
        
        
        return health <= 0 ? -1 : health;
    }
}