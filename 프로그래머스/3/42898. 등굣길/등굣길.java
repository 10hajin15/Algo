class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1000000007;
        int[][] dp = new int[n][m];

        for (int[] puddle : puddles) {
            int x = puddle[0] - 1;
            int y = puddle[1] - 1;
            dp[y][x] = -1;
        }

        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            if (dp[i][0] == -1) dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
        }

        for (int j = 1; j < m; j++) {
            if (dp[0][j] == -1) dp[0][j] = 0;
            else dp[0][j] = dp[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
