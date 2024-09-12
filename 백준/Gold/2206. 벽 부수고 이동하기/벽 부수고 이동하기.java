import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static String[] arr;
    static int[][][] visited;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited[0][0][1] = 1;
        q.offer(new int[] {0,0,1});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int di = now[0];
            int dj = now[1];
            int cnt = now[2];

            if(di==N-1 && dj==M-1) return;

            for(int i=0; i<4; i++) {
                int ni = di+dirs[i][0];
                int nj = dj+dirs[i][1];

                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;

                if(arr[ni].charAt(nj) == '1') {
                    if(cnt <= 0) continue;
                    if(visited[ni][nj][cnt-1]!=0) continue;
                    q.offer(new int[] {ni, nj, cnt-1});
                    visited[ni][nj][cnt-1] = visited[di][dj][cnt] + 1;
                } else {
                    if(visited[ni][nj][cnt]!=0) continue;
                    q.offer(new int[] {ni, nj, cnt});
                    visited[ni][nj][cnt] = visited[di][dj][cnt] + 1;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new String[N];

        for(int i=0; i<N; i++) {
            arr[i] = br.readLine();
        }

        visited = new int[N][M][2];

        bfs();

        if(visited[N-1][M-1][0] == 0 && visited[N-1][M-1][1] == 0) {
            System.out.println(-1);
        } else if(visited[N-1][M-1][0] == 0) {
            System.out.println(visited[N-1][M-1][1]);
        } else if(visited[N-1][M-1][1] == 0) {
            System.out.println(visited[N-1][M-1][0]);
        } else {
            System.out.println(Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]));
        }
    }
}