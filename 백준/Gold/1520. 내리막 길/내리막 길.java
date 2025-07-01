import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] ans;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, arr[0][0]));
        ans[0][0] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int[] dir : dirs) {
                int ni = now.i + dir[0];
                int nj = now.j + dir[1];

                if(ni<0||ni>=N||nj<0||nj>=M) continue;
                if(now.h <= arr[ni][nj]) continue;

                if (ans[ni][nj] == 0) {
                    q.add(new Node(ni, nj, arr[ni][nj]));
                }

                ans[ni][nj] += ans[now.i][now.j];
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        ans = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(ans[N - 1][M - 1]);
    }

    static class Node implements Comparable<Node> {
        int i, j, h;

        Node(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(-h, -n.h);
        }
    }
}