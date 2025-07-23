import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1000000007;
    static int N, M, P;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dp = new long[P + 1][N + 1];
        dp[0][0] = 1;

        for (int i = 1; i < P + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                dp[i][j] += dp[i - 1][j - 1] * (N - (j - 1));
                dp[i][j] %= MOD;

                if (j > M) {
                    dp[i][j] += dp[i - 1][j] * (j - M);
                    dp[i][j] %= MOD;
                }
            }
        }

        System.out.println(dp[P][N]);
    }
}