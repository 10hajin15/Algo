import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static boolean[][] arr = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> dirList = new ArrayList<>();
            dirList.add(d);

            for (int i = 0; i < g; i++) {
                for (int j = dirList.size() - 1; j >= 0; j--) {
                    dirList.add((dirList.get(j) + 1) % 4);
                }
            }

            arr[y][x] = true;
            for (int dir : dirList) {
                y += dirs[dir][0];
                x += dirs[dir][1];
                arr[y][x] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j] && arr[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
