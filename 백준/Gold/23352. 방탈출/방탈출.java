import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int length = Integer.MIN_VALUE;
    static int answer = Integer.MIN_VALUE;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs(int si, int sj) {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{si, sj, 1});
        visit[si][sj] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int i = now[0];
            int j = now[1];
            int len = now[2];

            for(int[] dir: dirs) {
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
                if(arr[ni][nj] == 0) continue;
                if(visit[ni][nj]) continue;

                visit[ni][nj] = true;

                if(length <= len) {
                    if(length == len) {
                        answer = Math.max(answer, arr[si][sj] + arr[ni][nj]);
                    } else {
                        length = len;
                        answer = arr[si][sj] + arr[ni][nj];
                    }
                }

                q.add(new int[]{ni, nj, len+1});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }
}
