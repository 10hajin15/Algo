import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[n][m]).append("\n");

        Stack<Character> stack = new Stack<>();
        while (n > 0 && m > 0) {
            if(n == 0 || m == 0) break;

            if(dp[n][m] == dp[n-1][m]) {
                n--;
            } else if (dp[n][m] == dp[n][m - 1]) {
                m--;
            } else {
                stack.push(s1.charAt(n - 1));
                n--;
                m--;
            }
        }

        while(!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb.toString());
    }
}