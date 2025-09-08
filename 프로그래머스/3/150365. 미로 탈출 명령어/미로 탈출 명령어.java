class Solution {
    static int[][] dirs = {{1,0},{0,-1},{0,1},{-1,0}};
    static int ei, ej;
    static String answer;
    static int N, M, K;
    
    char getDir(int idx) {
        if(idx == 0) return 'd';
        else if(idx == 1) return 'l';
        else if(idx == 2) return 'r';
        else return 'u';
    }
    
    void dfs(int depth, char[] tmp, int di, int dj) {
        if(answer != "") return;
        
        int dist = Math.abs(di-ei) + Math.abs(dj-ej);
        if(K-depth < dist || dist % 2 != (K-depth) % 2) return;
        
        if(depth == K) {
            if(di == ei && dj == ej) {
                answer = new String(tmp);
            }
            return;
        }
        
        for(int i=0; i<4; i++) {
            int ni = di + dirs[i][0];
            int nj = dj + dirs[i][1];
            if(1<=ni && ni<=N && 1<=nj && nj<=M) {
                tmp[depth] = getDir(i);
                dfs(depth+1, tmp, ni, nj);
            }
        }
    }
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.N = n;
        this.M = m;
        this.K = k;
        this.ei = r;
        this.ej = c;
        this.answer = "";
        
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if(k < dist || dist % 2 != k % 2) return "impossible";
        
        dfs(0, new char[k], x, y);
         
        return answer;
    }
}