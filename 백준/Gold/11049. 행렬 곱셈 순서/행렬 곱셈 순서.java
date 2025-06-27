import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Matrix[] m;
    static int[][] dp;

    static int dfs(int s, int e) {
        if(dp[s][e] != -1) return dp[s][e];
        if(s == e) return 0;
        if(s+1 == e) return m[s].r * m[s].c * m[e].c;

        int result = Integer.MAX_VALUE;
        for (int i = s; i < e; i++) {
            result = Math.min(result, dfs(s, i) + dfs(i + 1, e) + (m[s].r * m[i].c * m[e].c));
        }

        return dp[s][e] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        m = new Matrix[N + 1];
        dp = new int[N + 1][N + 1];

        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            m[i] = new Matrix(r, c);
        }

        System.out.println(dfs(1, N));
    }

    static class Matrix {
        int r, c;

        Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}