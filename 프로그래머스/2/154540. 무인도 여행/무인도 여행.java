import java.util.*;

class Solution {
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    static String[] maps;
    static int N, M;
    static boolean[][] visited;
    
    public int bfs(int si, int sj) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {si, sj});
        visited[si][sj] = true;
        int result = 0;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            int di = now[0];
            int dj = now[1];
            
            result += maps[di].charAt(dj) - '0';
            
            for(int[] dir: dirs) {
                int ni = di+dir[0];
                int nj = dj+dir[1];
                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
                if(visited[ni][nj] || maps[ni].charAt(nj) == 'X') continue;
                visited[ni][nj] = true;
                q.add(new int[] {ni, nj});
            }    
        }
        
        return result;
    }
    
    public int[] solution(String[] maps) {
        this.maps = maps;
        N = maps.length;
        M = maps[0].length();
        visited = new boolean[N][M];
        
        List<Integer> lst = new ArrayList<>();
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    lst.add(bfs(i, j));
                }
            }
        }
        
        if(lst.size() == 0) return new int[] {-1};
        
        int[] answer = new int[lst.size()];
        
        for(int i=0; i<lst.size(); i++) {
            answer[i] = lst.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}