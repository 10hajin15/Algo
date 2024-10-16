import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxCustomers = C + 100;
        int[] dp = new int[maxCustomers+1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for(int[] city: arr) {
            int cost = city[0];
            int customer = city[1];

            for(int j=customer; j<=maxCustomers; j++) {
                dp[j] = Math.min(dp[j], dp[j-customer]+cost);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = C; i <= maxCustomers; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }
}