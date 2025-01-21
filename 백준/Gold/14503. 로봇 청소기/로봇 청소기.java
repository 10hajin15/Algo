import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int d;
    static int ri, rj;
    static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    static int[][] map;
    static int ans;

    static void move() {
        while(true) {
            if(map[ri][rj] == 0) {
                map[ri][rj] = 2;
                ans++;
            }

            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int ni = ri + dirs[d][0];
                int nj = rj + dirs[d][1];

                if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == 0) {
                    ri = ni;
                    rj = nj;
                    cleaned = true;
                    break;
                }
            }

            if (!cleaned) {
                int ni = ri - dirs[d][0];
                int nj = rj - dirs[d][1];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 1) {
                    break;
                }
                ri = ni;
                rj = nj;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        ri = Integer.parseInt(st.nextToken());
        rj = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move();

        System.out.println(ans);
    }
}