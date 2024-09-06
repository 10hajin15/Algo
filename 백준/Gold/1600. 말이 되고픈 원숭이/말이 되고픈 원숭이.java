import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    static int K, N, M;
    static int[][] horse = {{-2,-1},{-1,-2},{-2,1},{-1,2},{1,-2},{2,-1},{2,1},{1,2}};
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    static boolean isRange(int x, int y) {
        if (x<0 || x>=N || y<0 || y>=M) return false;
        return true;
    }

    static void bfs() {
        boolean[][][] visited = new boolean[N][M][K+1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, K));
        visited[0][0][K] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();
            int di = now.x;
            int dj = now.y;
            int cnt = now.cnt;
            int k = now.k;

            if(di == N-1 && dj == M-1) {
                System.out.println(cnt);
                return;
            }

            if (k > 0) {
                for(int i=0; i<8; i++) {
                    int ni = di + horse[i][0];
                    int nj = dj + horse[i][1];

                    if(isRange(ni, nj) && !visited[ni][nj][k-1] && arr[ni][nj] != 1) {
                        visited[ni][nj][k-1] = true;
                        q.offer(new Node(ni,nj,cnt+1, k-1));
                    }
                }
            }

            for(int i=0; i<4; i++) {
                int ni = di+dirs[i][0];
                int nj = dj+dirs[i][1];

                if(isRange(ni, nj) && arr[ni][nj] != 1 && !visited[ni][nj][k]) {
                    visited[ni][nj][k] = true;
                    q.offer(new Node(ni, nj, cnt+1, k));
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static class Node {
        int x, y, cnt, k;

        Node(int x, int y, int cnt, int k) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
}