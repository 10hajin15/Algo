import java.io.*;
import java.util.*;

public class Solution {
    static int[][] arr;
    static List<int[]> core;
    static int ans, maxConnectedCores, N;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    static boolean canPlaceWire(int x, int y, int d) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += dirs[d][0];
            ny += dirs[d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                return true;
            }

            if (arr[nx][ny] != 0) {
                return false;
            }
        }
    }

    static int placeWire(int x, int y, int d, int status) {
        int nx = x;
        int ny = y;
        int length = 0;

        while (true) {
            nx += dirs[d][0];
            ny += dirs[d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;

            arr[nx][ny] = status;
            length++;
        }

        return length;
    }

    static void backtrack(int index, int connectedCores, int wireLength) {
        if (index == core.size()) {
            if (connectedCores > maxConnectedCores ||
                    (connectedCores == maxConnectedCores && wireLength < ans)) {
                maxConnectedCores = connectedCores;
                ans = wireLength;
            }
            return;
        }

        int x = core.get(index)[0];
        int y = core.get(index)[1];

        for (int i = 0; i < 4; i++) {
            if (canPlaceWire(x, y, i)) {
                int length = placeWire(x, y, i, 2);  // 전선 설치 (2로 표시)
                backtrack(index + 1, connectedCores + 1, wireLength + length);
                placeWire(x, y, i, 0);  // 전선 제거
            }
        }

        // 전선을 설치하지 않고 넘어가는 경우
        backtrack(index + 1, connectedCores, wireLength);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            core = new ArrayList<>();
            ans = Integer.MAX_VALUE;
            maxConnectedCores = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;
                    if (arr[i][j] == 1) {
                        core.add(new int[]{i, j});
                    }
                }
            }

            backtrack(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}