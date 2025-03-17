import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static int cnt;
    static int[][] arr;
    static Queue<int[]> tomatoes;

    static int bfs() {
        while (!tomatoes.isEmpty()) {
            int[] now = tomatoes.poll();

            for (int[] dir : dirs) {
                int ni = now[0] + dir[0];
                int nj = now[1] + dir[1];

                if(ni<0 || ni>=N || nj<0 || nj>=M) continue;

                if(arr[ni][nj] == 0) {
                    tomatoes.add(new int[]{ni, nj});
                    arr[ni][nj] = arr[now[0]][now[1]] + 1;
                    cnt -= 1;
                    if(cnt == 0) return arr[ni][nj]-1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        tomatoes = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) tomatoes.add(new int[]{i, j});
                else if(arr[i][j] == 0) cnt++;
            }
        }

        if(cnt == 0) System.out.println(0);
        else System.out.println(bfs());
    }
}