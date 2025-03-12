class Solution {
    public int solution(int[] players, int m, int k) {
        int[] server = new int[24+k];
        int cur = 0;
        int answer = 0;
        
        for(int i=0; i<24; i++) {
            cur += server[i];
            
            if(players[i] == 0) continue;
            
            if(players[i] >= m) {
                int tmp = players[i] / m;
                if(tmp <= cur) continue;
                tmp -= cur;
                cur += tmp;
                answer += tmp;
                server[i+k] += (-1 * tmp);
            }
        }
        
        return answer;
    }
}