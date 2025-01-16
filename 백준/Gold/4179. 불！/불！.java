import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static Queue<int[]> jihunQueue = new LinkedList<>();
    static Queue<int[]> fireQueue = new LinkedList<>();
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    jihunQueue.add(new int[]{i, j});
                } else if (map[i][j] == 'F') {
                    fireQueue.add(new int[]{i, j});
                }
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    static int bfs() {
        int time = 0;

        while (!jihunQueue.isEmpty()) {
            time++;

            int fireSize = fireQueue.size();
            for (int i = 0; i < fireSize; i++) {
                int[] fire = fireQueue.poll();
                for (int[] dir : dirs) {
                    int ni = fire[0] + dir[0];
                    int nj = fire[1] + dir[1];

                    if (ni < 0 || ni >= R || nj < 0 || nj >= C) continue;
                    if (map[ni][nj] == '.') {
                        map[ni][nj] = 'F';
                        fireQueue.add(new int[]{ni, nj});
                    }
                }
            }

            int jihunSize = jihunQueue.size();
            for (int i = 0; i < jihunSize; i++) {
                int[] jihun = jihunQueue.poll();
                for (int[] dir : dirs) {
                    int ni = jihun[0] + dir[0];
                    int nj = jihun[1] + dir[1];

                    if (ni < 0 || ni >= R || nj < 0 || nj >= C) {
                        return time;
                    }

                    if (map[ni][nj] == '.') {
                        map[ni][nj] = 'J';
                        jihunQueue.add(new int[]{ni, nj});
                    }
                }
            }
        }

        return -1;
    }
}