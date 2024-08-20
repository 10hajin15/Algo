import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, B, ans;
    static int[] height;

    static void dfs(int n, int sum) {
        if(sum >= B) {
            ans = Math.min(sum, ans);
            return;
        }

        if(n == N) {
            if(sum >= B) ans = Math.min(sum, ans);
            return;
        }

        dfs(n+1, sum+height[n]);
        dfs(n+1, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
            st = new StringTokenizer(br.readLine());

            ans = 0;
            for(int i=0; i<N; i++){
                height[i] = Integer.parseInt(st.nextToken());
                ans += height[i];
            }

            if(ans == B) {
                ans = 0;
            } else {
                dfs(0, 0);
            }

            System.out.println("#"+tc+" "+(ans-B));
        }
    }
}