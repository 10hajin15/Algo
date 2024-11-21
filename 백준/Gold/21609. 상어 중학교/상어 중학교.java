import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static Point bfs(int si, int sj, boolean flag) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> rainbowBlocks = new ArrayList<>();
        q.add(new int[]{si, sj});
        visited[si][sj] = true;

        int zeroCnt = 0;
        int size = 0;
        int color = arr[si][sj];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int di = now[0];
            int dj = now[1];

            if (flag) arr[di][dj] = -2;
            size++;

            if (arr[di][dj] == 0) {
                zeroCnt++;
                rainbowBlocks.add(new int[]{di, dj});
            }

            for (int i = 0; i < 4; i++) {
                int ni = di + dirs[i][0];
                int nj = dj + dirs[i][1];

                if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                if (visited[ni][nj]) continue;
                if (arr[ni][nj] < 0 || (arr[ni][nj] != color && arr[ni][nj] != 0)) continue;

                visited[ni][nj] = true;
                q.add(new int[]{ni, nj});
            }
        }

        for (int[] rb : rainbowBlocks) {
            visited[rb[0]][rb[1]] = false;
        }

        return new Point(new int[]{si, sj}, zeroCnt, size);
    }

    public static void gravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                if (arr[i][j] < 0) continue;
                int ni = i;
                while (ni + 1 < N && arr[ni + 1][j] == -2) {
                    arr[ni + 1][j] = arr[ni][j];
                    arr[ni][j] = -2;
                    ni++;
                }
            }
        }
    }

    public static void rotation() {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[N - j - 1][i] = arr[i][j];
            }
        }
        arr = rotated;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            Point start = new Point(new int[]{-1, -1}, 0, 1);

            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] || arr[i][j] <= 0) continue;
                    Point now = bfs(i, j, false);
                    if (now.size > start.size) {
                        start = now;
                    } else if (now.size == start.size) {
                        if (now.zeroCnt > start.zeroCnt) {
                            start = now;
                        } else if (now.zeroCnt == start.zeroCnt) {
                            if (now.xy[0] > start.xy[0] || (now.xy[0] == start.xy[0] && now.xy[1] > start.xy[1])) {
                                start = now;
                            }
                        }
                    }
                }
            }

            if (start.size == 1) break;

            visited = new boolean[N][N];
            bfs(start.xy[0], start.xy[1], true);
            gravity();
            rotation();
            gravity();

            answer += (start.size * start.size);
        }

        System.out.println(answer);
    }

    public static class Point {
        int[] xy;
        int zeroCnt;
        int size;

        public Point(int[] xy, int zeroCnt, int size) {
            this.xy = xy;
            this.zeroCnt = zeroCnt;
            this.size = size;
        }
    }
}