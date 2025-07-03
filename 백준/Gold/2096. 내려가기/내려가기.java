import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] maxdp = new int[N][3];
        int[][] mindp = new int[N][3];
        for (int j = 0; j < 3; j++) {
            maxdp[0][j] = arr[0][j];
            mindp[0][j] = arr[0][j];
        }

        for (int i = 1; i < N; i++) {
            maxdp[i][0] = arr[i][0] + Math.max(maxdp[i - 1][0], maxdp[i - 1][1]);
            mindp[i][0] = arr[i][0] + Math.min(mindp[i - 1][0], mindp[i - 1][1]);

            maxdp[i][1] = arr[i][1] + Math.max(maxdp[i - 1][0], Math.max(maxdp[i - 1][1], maxdp[i - 1][2]));
            mindp[i][1] = arr[i][1] + Math.min(mindp[i - 1][0], Math.min(mindp[i - 1][1], mindp[i - 1][2]));

            maxdp[i][2] = arr[i][2] + Math.max(maxdp[i - 1][2], maxdp[i - 1][1]);
            mindp[i][2] = arr[i][2] + Math.min(mindp[i - 1][2], mindp[i - 1][1]);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            max = Math.max(max, maxdp[N - 1][j]);
            min = Math.min(min, mindp[N - 1][j]);
        }

        System.out.println(max + " " + min);
    }
}