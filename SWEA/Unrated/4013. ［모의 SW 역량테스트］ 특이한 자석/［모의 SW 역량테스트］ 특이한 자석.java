import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());

            int[][] list = new int[4][8];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    list[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                int[] isTurn = new int[4];
                isTurn[num] = dir;

                for (int j = num - 1; j >= 0; j--) {
                    if (list[j][2] != list[j + 1][6]) {
                        isTurn[j] = -isTurn[j + 1];
                    } else {
                        break;
                    }
                }

                for (int j = num + 1; j < 4; j++) {
                    if (list[j][6] != list[j - 1][2]) {
                        isTurn[j] = -isTurn[j - 1];
                    } else {
                        break;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    if (isTurn[j] == 1) {
                        turnClockwise(list[j]);
                    } else if (isTurn[j] == -1) {
                        turnCounterClockwise(list[j]);
                    }
                }
            }

            int ans = 0;
            if (list[0][0] == 1) ans += 1;
            if (list[1][0] == 1) ans += 2;
            if (list[2][0] == 1) ans += 4;
            if (list[3][0] == 1) ans += 8;

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void turnClockwise(int[] magnet) {
        int temp = magnet[7];
        for (int i = 7; i > 0; i--) {
            magnet[i] = magnet[i - 1];
        }
        magnet[0] = temp;
    }

    public static void turnCounterClockwise(int[] magnet) {
        int temp = magnet[0];
        for (int i = 0; i < 7; i++) {
            magnet[i] = magnet[i + 1];
        }
        magnet[7] = temp;
    }
}