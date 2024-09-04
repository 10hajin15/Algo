import java.io.*;
import java.util.*;

public class Solution {
    static int[][] arr;
    static int N, M, ans;
    static List<int[]> houses;

    public static void calculateMaxHouses() {
        int maxK = N + (N - 1);

        for (int k = 1; k <= maxK; k++) {
            int serviceCost = k * k + (k - 1) * (k - 1);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int houseCount = 0;
                    for (int[] house : houses) {
                        int hi = house[0];
                        int hj = house[1];
                        if (Math.abs(hi - i) + Math.abs(hj - j) < k) {
                            houseCount++;
                        }
                    }
                    int profit = houseCount * M;
                    if (profit >= serviceCost) {
                        ans = Math.max(ans, houseCount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ans = 0;

            arr = new int[N][N];
            houses = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        houses.add(new int[]{i, j});
                    }
                }
            }

            calculateMaxHouses();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}