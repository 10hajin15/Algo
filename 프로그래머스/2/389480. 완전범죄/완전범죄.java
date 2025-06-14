import java.util.*;

class Solution {
    static int[][] info;
    static int n;
    static int m;
    static int answer;
    static Set<String> visited;

    void dfs(int i, int a, int b) {
        if(i == info.length) {
            answer = Math.min(answer, a);
            return;
        }
        
        String key = i+","+a+","+b;
        if(visited.contains(key)) return;
        

        if(info[i][0]+a < n) {
            dfs(i+1, a+info[i][0], b);
        }
        
        if(info[i][1]+b < m) {
            dfs(i+1, a, b+info[i][1]);
        }
        
        visited.add(key);
    }
    
    public int solution(int[][] info, int n, int m) {
        answer = Integer.MAX_VALUE;
        visited= new HashSet<>();
        
        this.info = info;
        this.n = n;
        this.m = m;
        
        dfs(0, 0, 0);
        
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        return answer;
    }
}