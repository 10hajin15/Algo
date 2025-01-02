import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs8 = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] dirsDiag4 = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveClouds(clouds, d, s);

            rain(clouds);

            copyWater(clouds);

            clouds = makeClouds(clouds);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += arr[i][j];
            }
        }

        System.out.println(ans);
    }

    static void moveClouds(List<int[]> clouds, int d, int s) {
        for(int[] cloud: clouds) {
            cloud[0] = (cloud[0] + dirs8[d - 1][0] * s) % N;
            cloud[1] = (cloud[1] + dirs8[d - 1][1] * s) % N;

            if (cloud[0] < 0) cloud[0] += N;
            if (cloud[1] < 0) cloud[1] += N;
        }
    }

    static void rain(List<int[]> clouds) {
        for (int[] cloud : clouds) {
            arr[cloud[0]][cloud[1]] += 1;
        }
    }

    static void copyWater(List<int[]> clouds) {
        for (int[] cloud: clouds) {
            int cnt = 0;

            for(int[] dir: dirsDiag4) {
                int ni = cloud[0] + dir[0];
                int nj = cloud[1] + dir[1];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                if (arr[ni][nj] > 0) cnt++;
            }

            arr[cloud[0]][cloud[1]] += cnt;
        }
    }

    static List<int[]> makeClouds(List<int[]> prevClouds) {
        boolean[][] wasCloud = new boolean[N][N];
        for (int[] prev: prevClouds) {
            wasCloud[prev[0]][prev[1]] = true;
        }

        List<int[]> newClouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!wasCloud[i][j] && arr[i][j] >= 2) {
                    newClouds.add(new int[]{i, j});
                    arr[i][j] -= 2;
                }
            }
        }
        return newClouds;
    }

}