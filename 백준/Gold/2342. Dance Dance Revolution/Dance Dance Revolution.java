import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static List<Integer> steps = new ArrayList<>();;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(true) {
            int n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            steps.add(n);
        }

        int N = steps.size();

        dp = new int[N+1][5][5];
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < N; i++) {
            int move = steps.get(i);
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    if(dp[i][left][right] == INF) continue;

                    if (right != move) {
                        int power = dp[i][left][right] + getCost(left, move);
                        dp[i + 1][move][right] = Math.min(dp[i + 1][move][right], power);
                    }

                    if (left != move) {
                        int power = dp[i][left][right] + getCost(right, move);
                        dp[i + 1][left][move] = Math.min(dp[i + 1][left][move], power);
                    }
                }
            }
        }

        int result = INF;
        for (int left = 0; left < 5; left++) {
            for (int right = 0; right < 5; right++) {
                result = Math.min(result, dp[N][left][right]);
            }
        }

        System.out.println(result);
    }

    static int getCost(int from, int to) {
        if(from == 0) return 2;
        if(from == to) return 1;
        if((from == 1 && to == 3) || (from == 3 && to == 1) ||
                (from == 2 && to == 4) || (from == 4 && to == 2)) return 4;
        return 3;
    }
}