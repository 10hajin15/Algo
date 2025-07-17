import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int MOD = 1000000007;

        long[][][] dp = new long[101][101][101];
        dp[1][1][1] = dp[2][1][2] = dp[2][2][1] = 1;

        for (int i = 3; i < N+1; i++) {
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    dp[i][l][r] += (dp[i - 1][l - 1][r] + dp[i - 1][l][r - 1] + (dp[i - 1][l][r] * (i - 2))) % MOD;
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}