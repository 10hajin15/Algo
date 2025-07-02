import java.util.*;

class Solution {
    int[][] infos;
    int N;
    int aLimit, bLimit;
    int answer;
    Set<String> visited;
    
    void dfs(int depth, int a, int b) {
        if(depth == N) {
            answer = Math.min(a, answer);
            return;
        }
        
        String key = depth+","+a+","+b;
        if(visited.contains(key)) return;
        
        if(infos[depth][0] + a < aLimit) dfs(depth+1, a+infos[depth][0], b);
        if(infos[depth][1] + b < bLimit) dfs(depth+1, a, b+infos[depth][1]);
        
        visited.add(key);
    }
    
    public int solution(int[][] info, int n, int m) {
        infos = info;
        answer = Integer.MAX_VALUE;
        aLimit = n;
        bLimit = m;
        N = info.length;
        visited = new HashSet<>();
        
        dfs(0, 0, 0);
        
        if(answer == Integer.MAX_VALUE) answer = -1;
        
        return answer;
    }
}