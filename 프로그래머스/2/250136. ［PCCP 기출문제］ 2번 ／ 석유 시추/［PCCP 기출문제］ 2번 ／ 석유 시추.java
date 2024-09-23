import java.util.*;

class Solution {
    static int[][] arr;
    static int cnt;
    static int N, M;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    
    static public int bfs(int si, int sj) {
        int oil = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {si, sj});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            arr[now[0]][now[1]] = cnt;
            oil++;
            
            for(int i=0; i<4; i++) {
                int ni = now[0]+dirs[i][0];
                int nj = now[1]+dirs[i][1];
                
                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
                
                if(arr[ni][nj] == 1) {
                    arr[ni][nj] = cnt;
                    q.offer(new int[] {ni, nj});
                } 
            }
        }

        return oil;
    }
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        arr = new int[N][M];
        arr = land;
        
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(0);
        
        cnt = 2;
        for(int i =0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(land[i][j] == 1) {
                    int oil = bfs(i, j);
                    list.add(oil);
                    cnt++;
                }               
            }
        }
        
        int answer = 0;
        for(int j=0; j<M; j++) {
            int tAns = 0;
            Set<Integer> set = new HashSet<>();
            for(int i=0; i<N; i++) {
                if(arr[i][j] != 0 && !set.contains(arr[i][j])) {
                    set.add(arr[i][j]);
                    tAns += list.get(arr[i][j]);
                }
            }
            answer = Math.max(answer, tAns);
        }
        
        return answer;
    }
}