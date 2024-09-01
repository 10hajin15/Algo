import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] arr = new String[N];
        for(int i=0; i<N; i++) {
            arr[i] = br.readLine();
        }

        int[][] visited = new int[N][M];
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = 1;
        q.offer(new int[] {0,0});
        int ans = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            if(x==N-1 && y==M-1) {
                ans = visited[x][y];
                break;
            }

            for(int i=0; i<4; i++) {
                int nx = x+dirs[i][0];
                int ny = y+dirs[i][1];

                if (nx<0 || nx>=N || ny<0 || ny>=M) continue;

                if(visited[nx][ny] == 0 && arr[nx].charAt(ny) == '1') {
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = visited[x][y] + 1;
                }
            }
        }
        System.out.println(ans);
    }
}