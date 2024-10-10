import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            for (int i = 0; i < N; i++) {
                if (canBuildRunway(arr[i], N, X)) {
                    answer++;
                }
            }

            for (int j = 0; j < N; j++) {
                int[] column = new int[N];
                for (int i = 0; i < N; i++) {
                    column[i] = arr[i][j];
                }
                if (canBuildRunway(column, N, X)) {
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static boolean canBuildRunway(int[] terrain, int N, int X) {
        boolean[] slopeUsed = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int heightDiff = terrain[i] - terrain[i + 1];

            if (heightDiff == 0) {
                continue;
            } else if (heightDiff == 1) {
                if (i + 1 < N && canPlaceSlope(terrain, slopeUsed, i + 1, X, terrain[i + 1], true)) {
                    markSlope(slopeUsed, i + 1, X, true);
                } else {
                    return false;
                }
            } else if (heightDiff == -1) {
                if (i - X + 1 >= 0 && canPlaceSlope(terrain, slopeUsed, i, X, terrain[i], false)) {
                    markSlope(slopeUsed, i, X, false);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean canPlaceSlope(int[] terrain, boolean[] slopeUsed, int startIndex, int X, int targetHeight, boolean ascending) {
        if (ascending) {
            for (int j = 0; j < X; j++) {
                if (startIndex + j >= terrain.length || terrain[startIndex + j] != targetHeight || slopeUsed[startIndex + j]) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < X; j++) {
                if (startIndex - j < 0 || terrain[startIndex - j] != targetHeight || slopeUsed[startIndex - j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void markSlope(boolean[] slopeUsed, int startIndex, int X, boolean ascending) {
        if (ascending) {
            for (int j = 0; j < X; j++) {
                slopeUsed[startIndex + j] = true;
            }
        } else {
            for (int j = 0; j < X; j++) {
                slopeUsed[startIndex - j] = true;
            }
        }
    }
}