import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for(int num: nums) {
            for (int i = 0; i < k+1; i++) {
                if(i >= num) dp[i] += dp[i - num];
            }
        }

        System.out.println(dp[k]);
    }
}