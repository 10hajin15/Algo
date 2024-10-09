import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N, M, R;
    static int[][] arr;
    static char[][] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        ans = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                ans[i][j] = 'S';
            }
        }

        int total = 0;

        for (int r = 0; r < R; r++) {
            String[] attack = br.readLine().split(" ");


            int ai = Integer.parseInt(attack[0])-1;
            int aj = Integer.parseInt(attack[1])-1;
            String aDir = attack[2];

            if(ans[ai][aj] == 'S') {
                int dIdx = getDirIndex(aDir);
                int cnt = arr[ai][aj];

                ans[ai][aj] = 'F';
                total += 1;

                int ni = ai, nj = aj;

                while(cnt > 1) {
                    ni += dirs[dIdx][0];
                    nj += dirs[dIdx][1];

                    if(ni<0 || ni>=N || nj<0 || nj>=M) break;

                    if(ans[ni][nj] == 'S') {
                        total += 1;
                        ans[ni][nj] = 'F';
                        cnt = Math.max(cnt-1, arr[ni][nj]);
                    } else {
                        cnt--;
                    }
                }
            }

            String[] defense = br.readLine().split(" ");
            int di = Integer.parseInt(defense[0])-1;
            int dj = Integer.parseInt(defense[1])-1;
            ans[di][dj] = 'S';
        }

        sb.append(total).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int getDirIndex(String dir) {
        switch (dir) {
            case "E": return 0;
            case "W": return 1;
            case "S": return 2;
            case "N": return 3;
        }
        return -1;
    }
}