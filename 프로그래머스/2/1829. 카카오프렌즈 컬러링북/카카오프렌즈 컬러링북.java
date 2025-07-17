import java.util.*;

class Solution {
    static boolean[][] visited;
    static int numberOfArea, maxSizeOfOneArea;
    static int N, M;
    static int[][] maps;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    
    void bfs(int si, int sj) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {si, sj});
        visited[si][sj] = true;
        
        int cnt = 0;
        int num = maps[si][sj];
        while(!q.isEmpty()) {
            int[] now = q.poll();
            cnt++;
            
            for(int[] dir: dirs) {
                int ni = now[0] + dir[0];
                int nj = now[1] + dir[1];
                if(ni<0 || ni>=N || nj<0 || nj>=M || visited[ni][nj]) continue;
                if(num != maps[ni][nj]) continue;
                visited[ni][nj] = true;
                q.add(new int[] {ni, nj});
            }
        }
        
        if(maxSizeOfOneArea < cnt) maxSizeOfOneArea = cnt;
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        N = m;
        M = n;
        visited = new boolean[N][M];
        maps = picture;
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(maps[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j);
                    numberOfArea++;
                }
            }
        }
        

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}