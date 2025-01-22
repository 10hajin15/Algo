import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        int[][] sum = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[i+1][j+1] = arr[i][j] + sum[i][j+1] + sum[i+1][j] - sum[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int k = 1; k <= N; k++) {
            for (int i = 0; i < N-k+1; i++) {
                for (int j = 0; j < N-k+1; j++) {
                    int tmp = sum[i + k][j + k]
                            - sum[i][j + k]
                            - sum[i + k][j]
                            + sum[i][j];
                    ans = Math.max(ans, tmp);
                }
            }
        }

        System.out.println(ans);
    }
}