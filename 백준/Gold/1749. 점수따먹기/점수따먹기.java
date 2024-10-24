import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int[] colSum = new int[M];

            for (int j = i; j < N; j++) {
                for (int col = 0; col < M; col++) {
                    colSum[col] += arr[j][col];
                }

                int currentMax = kadane(colSum);

                maxSum = Math.max(maxSum, currentMax);
            }
        }

        System.out.println(maxSum);
    }

    public static int kadane(int[] arr) {
        int maxCur = arr[0];
        int maxTotal = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxCur = Math.max(arr[i], maxCur + arr[i]); // 현재까지 최대 합
            maxTotal = Math.max(maxTotal, maxCur);
        }

        return maxTotal;
    }
}