import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[][] arr;
    static int ans;
    static int N;
    static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    static void dfs(int si, int sj, int len, boolean[][] visited) {

        for(int i=0; i<4; i++) {
            int ni = si+dirs[i][0];
            int nj = sj+dirs[i][1];

            if(0>ni || ni>=N || 0>nj || nj>=N) continue;
            if(visited[ni][nj]) continue;

            if(arr[ni][nj] < arr[si][sj]) {
                visited[ni][nj] = true;
                if(ans < len+1) {
                    ans = len+1;
                }
                dfs(ni, nj, len+1, visited);
                visited[ni][nj] = false;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            ans = 1;

            Queue<int[]> q = new LinkedList<>();
            int maxHeight = 0;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if(num>maxHeight) {
                        maxHeight = num;
                        q.clear();
                    }
                    if(num == maxHeight) {
                        q.offer(new int[]{i, j});
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] now = q.poll();
                int si = now[0], sj = now[1];
                int k = K;

                while(k > 0) {
                    for(int i=0; i<N; i++) {
                        for(int j=0; j<N; j++) {
                            boolean[][] visited = new boolean[N][N];
                            visited[si][sj] = true;
                            arr[i][j] -= k;
                            dfs(si, sj, 1, visited);
                            arr[i][j] += k;
                        }
                    }
                    k--;
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}