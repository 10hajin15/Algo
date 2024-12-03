import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N, M, K;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    static int bfs(int si, int sj) {
        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(si, sj, 0, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if(now.x == N-1 && now.y == M-1) {
                return now.dist;
            }

            for(int i=0; i<4; i++) {
                int ni = now.x + dirs[i][0];
                int nj = now.y + dirs[i][1];

                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;

                if(arr[ni][nj] == 0 && !visited[ni][nj][now.k]) {
                    visited[ni][nj][now.k] = true;
                    q.add(new Point(ni, nj, now.k, now.dist+1));
                } else {
                    if(now.k < K && !visited[ni][nj][now.k+1]) {
                        visited[ni][nj][now.k+1] = true;
                        q.add(new Point(ni, nj, now.k+1, now.dist+1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String num = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = num.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }

    static class Point {
        int x, y, k, dist;

        public Point(int x, int y, int k, int dist) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.dist = dist;
        }
    }

}