import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] desert = new int[M][N];
        int[][] dp = new int[N][M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                desert[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dp[0][i] = desert[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int max = 0;
                int val = 0;
                for(int k=0; k<M; k++) {
                    if(k!=j) val = dp[i-1][k] + desert[j][i];
                    else val = dp[i-1][k] + desert[j][i]/2;

                    if (val > max) {
                       max = val;
                    }
                }
                dp[i][j] = max;
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, dp[N-1][i]);
        }

        System.out.println(ans);
    }
}
