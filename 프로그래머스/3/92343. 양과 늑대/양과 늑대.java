class Solution {
    static int answer;
    static int[][] gedges;
    static int[] ginfo;
    static boolean[] visited;
    
    void dfs(int sheep, int wolf) {
        if(sheep > wolf) {
            answer = Math.max(answer, sheep);
        } else {
            return;
        }
        
        for(int[] edge: gedges) {
            int p = edge[0];
            int c = edge[1];
            if(visited[p] && !visited[c]) {
                visited[c] = true;
                if(ginfo[c] == 0) dfs(sheep+1, wolf);
                else dfs(sheep, wolf+1);
                visited[c] = false;
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        ginfo = info;
        gedges = edges;
        visited = new boolean[n];
        visited[0] = true;
        dfs(1, 0);
        return answer;
    }
}