class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int time = 1;
        int maxHealth = health;
        for(int[] now: attacks) {
            int t = now[0] - time;
            health += ((bandage[1]*t)+(t/bandage[0]*bandage[2]));
            if(health>=maxHealth) health = maxHealth;
            
            health -= now[1];
            if(health <= 0) break;
                       
            time = now[0]+1;
        }
        
        return health<=0? -1: health;
    }
}