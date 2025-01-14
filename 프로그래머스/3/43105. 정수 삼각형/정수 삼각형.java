import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][];
        
        dp[0] = new int[1];
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < N; i++) {
            dp[i] = new int[i + 1];
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) dp[i][j] = dp[i - 1][0] + triangle[i][j];
                else if (j == i) dp[i][j] = dp[i - 1][i - 1] + triangle[i][j];
                else dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }
        
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(dp[N - 1][i], answer);
        }

        return answer;
    }
}
