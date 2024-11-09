import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] costs = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken()); // 빨강 비용
            costs[i][1] = Integer.parseInt(st.nextToken()); // 초록 비용
            costs[i][2] = Integer.parseInt(st.nextToken()); // 파랑 비용
        }

        // 세 가지 경우 각각을 계산
        int answer = Integer.MAX_VALUE;
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N][3];
            int INF = 1000001; // 오버플로우 방지를 위한 큰 값

            // 첫 번째 집의 색상을 고정하고, 해당 색상 외에는 큰 값으로 초기화
            for (int color = 0; color < 3; color++) {
                if (color == firstColor) {
                    dp[0][color] = costs[0][color];
                } else {
                    dp[0][color] = INF;
                }
            }

            // DP 점화식을 이용해 최소 비용 계산
            for (int i = 1; i < N; i++) {
                dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            // 마지막 집의 색상이 첫 번째 집의 색상과 다른 경우만 고려하여 최소 비용 갱신
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor != firstColor) {
                    answer = Math.min(answer, dp[N - 1][lastColor]);
                }
            }
        }

        System.out.println(answer);
    }
}