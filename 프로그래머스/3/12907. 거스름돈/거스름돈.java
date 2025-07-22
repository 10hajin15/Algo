class Solution {
    static final int MOD = 1000000007;
    
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int m: money) {
            for(int i=m; i<n+1; i++) {
                dp[i] += (dp[i-m] % MOD) ;
            }
        }
        
        return dp[n];
    }
}