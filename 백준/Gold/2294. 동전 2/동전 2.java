import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        Arrays.fill(dp, 10_001);
        dp[0] = 0;

        for (int coin : coins) {
            for (int j = coin; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        int answer = dp[K] > K ? -1 : dp[K];
        System.out.println(answer);
    }
}