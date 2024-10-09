import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static Map<String, Integer> strDir = new HashMap<>() {{
        put("E", 0);
        put("W", 1);
        put("S", 2);
        put("N", 3);
    }};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        char[][] ans = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans[i][j] = 'S';
            }
        }

        int total = 0;

        for (int r = 0; r < R; r++) {
            String[] attack = br.readLine().split(" ");
            String[] defense = br.readLine().split(" ");

            int ai = Integer.parseInt(attack[0])-1;
            int aj = Integer.parseInt(attack[1])-1;
            String aDir = attack[2];

            int cnt = ans[ai][aj] == 'F' ? 0 : arr[ai][aj] - 1;
            if(cnt > 0) total += 1;
            ans[ai][aj] = 'F';

            int ni = ai + dirs[strDir.get(aDir)][0];
            int nj = aj + dirs[strDir.get(aDir)][1];

            while(cnt > 0) {
                if(ni<0 || ni>=N || nj<0 || nj>=M) break;

                cnt--;
                if(ans[ni][nj] != 'F' && cnt < arr[ni][nj]-1) cnt = arr[ni][nj]-1;

                if(ans[ni][nj] == 'S') {
                    total += 1;
                    ans[ni][nj] = 'F';
                }

                ni += dirs[strDir.get(aDir)][0];
                nj += dirs[strDir.get(aDir)][1];
            }

            int di = Integer.parseInt(defense[0])-1;
            int dj = Integer.parseInt(defense[1])-1;
            ans[di][dj] = 'S';
        }

        System.out.println(total);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}