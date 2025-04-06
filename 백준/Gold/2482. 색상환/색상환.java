import java.io.*;

public class Main {
    static final int MOD = 1000000003;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][K+1];

        for (int i = 1; i < N+1; i++) {
            dp[i][1] = i;
        }

        for (int i = 2; i < N+1; i++) {
            for (int j = 2; j < K+1; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD;
            }
        }

        int answer;

        if(K==1) answer = N;
        else answer = (dp[N-3][K-1]+dp[N-1][K]) % MOD;

        System.out.println(answer);
    }
}