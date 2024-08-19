import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int[] oper, nums;
    static int mx;
    static int mn;


    static void dfs(int n, int num, int add, int sub, int mul, int div) {
        if(n == N) {
            mn = Math.min(num, mn);
            mx = Math.max(num, mx);
            return;
        }

        if (add > 0) dfs(n+1, num+nums[n], add-1, sub, mul, div);
        if (sub > 0) dfs(n+1, num-nums[n], add, sub-1, mul, div);
        if (mul > 0) dfs(n+1, num*nums[n], add, sub, mul-1, div);
        if (div > 0) dfs(n+1, num/nums[n], add, sub, mul, div-1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<= T; tc++) {
            mn = 100000000;
            mx = -100000000;
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            oper = new int[4];
            for(int i=0; i<4; i++) {
                oper[i] = Integer.parseInt(st.nextToken());
            }

            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            dfs(1, nums[0], oper[0], oper[1], oper[2], oper[3]);

            System.out.println(String.format("#%d %d", tc, mx-mn));
        }
    }
}