import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] tastes, cnts, pos;

    static int bSearch(int a, int b, int k) {
        int start = 0, end = 2*N-1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int tmp = Math.min(a, cnts[mid][0]) + Math.min(b, cnts[mid][1]);

            if(tmp < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tastes = new int[2][N];
        cnts = new int[N*2][2];
        pos = new int[N*2][2];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tastes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x=0, y=0, idx=0;
        int han=0, yang=0;
        while(x<N || y<N) {
            if(y>=N || (x<N && tastes[0][x] < tastes[1][y])) {
                cnts[idx][0] = ++han;
                cnts[idx][1] = yang;
                pos[idx][0] = 1;
                pos[idx++][1] = ++x;
            } else if(x>=N || (y<N && tastes[1][y] < tastes[0][x])) {
                cnts[idx][0] = han;
                cnts[idx][1] = ++yang;
                pos[idx][0] = 2;
                pos[idx++][1] = ++y;
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int ans = bSearch(a, b, k);

            sb.append(pos[ans][0]).append(" ").append(pos[ans][1]).append("\n");
        }

        System.out.print(sb);
    }
}