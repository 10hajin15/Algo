import java.util.*;

class Solution {
    static int N;
    static int[][] dice;
    static int sumCnt;
    static int[] answer;
    static Map<Integer, Integer> sumMap;
    
    void getSumList(int depth, int[] tmp, int[] dlst) {
        if(depth == N/2) {
            int sum = 0;
            for(int i=0; i<N/2; i++) {
                sum += dice[dlst[i]][tmp[i]];
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0)+1);
            return;
        }
        
        for(int i=0; i<6; i++) {
            tmp[depth] = i;
            getSumList(depth+1, tmp, dlst);
        }
    }
    
    
    void check(int[] dlst) {
        sumMap = new HashMap<>();
        getSumList(0, new int[N/2], dlst);
        Map<Integer, Integer> Adice = sumMap;
        
        sumMap = new HashMap<>();
        boolean[] b_dlst = new boolean[N];
        for(int i=0; i<N/2; i++) {
            b_dlst[dlst[i]] = true;
        }
        
        int[] t_dlst = new int[N/2];
        int idx = 0;
        for(int i=0; i<N; i++) {
            if(!b_dlst[i]) t_dlst[idx++] = i;
        }
        
        getSumList(0, new int[N/2], t_dlst);
        Map<Integer, Integer> Bdice = sumMap;
        
        int cnt = 0;
        for(int key1: Adice.keySet()) {
            for(int key2: Bdice.keySet()) {
                if(key1 > key2) {
                    cnt += (Adice.get(key1) * Bdice.get(key2));
                }
            }
        }

        if(sumCnt < cnt) {
            sumCnt = cnt;
            int[] ans = new int[N/2];
            for(int i=0; i<N/2; i++) {
                ans[i] = dlst[i] + 1;
            }
            answer = ans;
        }
    }
    
    void dfs(int depth, int start, int[] dlst) {
        if(depth == N/2) {
            check(dlst);
            return;
        }
        
        for(int i=start; i<N; i++) {
            dlst[depth] = i;
            dfs(depth+1, i+1, dlst);
        }
    }
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        this.dice = dice;
        sumCnt = 0;
        
        dfs(0, 0, new int[N/2]);
        
        return answer;
    }
}