class Solution {
    int answer;
    int[][] test;
    int[] result;
    int N;
    
    void calc(int[] lst) {
        for(int i=0; i<test.length; i++) {
            int cnt = 0;
            int[] tmp = test[i];
            
            for(int t: tmp) {
                for(int j=0; j<5; j++) {
                    if(t == lst[j]) {
                        cnt++;
                        break;
                    }
                }
            }
            
            if(cnt != result[i]) return;
        }
        
        answer++;
    }
    
    void dfs(int depth, int start, int[] lst) {
        if(depth == 5) {
            
            calc(lst);
            return;
        }
        
        for(int i=start; i<=N; i++) {
            lst[depth] = i;
            dfs(depth+1, i+1, lst);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        N = n;
        test = q;
        result = ans;
        
        dfs(0, 1, new int[5]);
        
        return answer;
    }
}