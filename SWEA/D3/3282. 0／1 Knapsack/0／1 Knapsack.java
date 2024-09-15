import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, K;
    static int[][] dp;
    static int[] w, v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            dp = new int[N+1][K+1];
            w = new int[N+1];
            v = new int[N+1];

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                w[i] = Integer.parseInt(st.nextToken());
                v[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=N; i++) {
                for(int j=0; j<=K; j++) {
                    dp[i][j] = dp[i-1][j];

                    if(w[i] <= j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
                    }
                }
            }

            System.out.println("#" + tc + " " + dp[N][K]);
        }
    }
}