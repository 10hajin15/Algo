import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int len = str.length();

        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[len + 1];
        int MOD = 1000000;

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= len; i++) {
            int oneDigit = str.charAt(i - 1) - '0';
            int twoDigit = Integer.parseInt(str.substring(i - 2, i));

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }

            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        System.out.println(dp[len]);
    }
}