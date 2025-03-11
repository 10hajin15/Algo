import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs(int si, int sj) {
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{si, sj});
        visited[si][sj] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for(int[] dir: dirs) {
                int ni = now[0] + dir[0];
                int nj = now[1] + dir[1];

                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
                if(visited[ni][nj]) continue;
                visited[ni][nj] = true;
                if(arr[ni][nj] == 0) q.add(new int[]{ni, nj});
                else arr[ni][nj] = 0;
            }
        }
    }

    static int check() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 1) count++;
            }
        }

        return count;
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

        int time = 0;
        int count = 0;
        while (true) {
            int tmp = check();

            if(tmp == 0) break;

            count = tmp;
            bfs(0, 0);
            time++;
        }

        System.out.println(time);
        System.out.println(count);
    }
}
