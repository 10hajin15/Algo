class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];

        int time = 0;
        int h = health;
        for(int[] attack: attacks) {
            int now = attack[0];
            int interval = now-time-1;

            h += x * interval + y * (interval/t);

            if(h > health) h = health;
            
            h -= attack[1];

            if(h <= 0) return -1;

            time = now;
        }

        return h;
    }
}