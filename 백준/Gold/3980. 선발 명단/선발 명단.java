import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] score;
    static int ans;

    static void dfs(int n, boolean[] visited, int sum) {
        if(n == 11) {
            ans = Math.max(sum, ans);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if(!visited[i] && score[n][i] != 0) {
                visited[i] = true;
                dfs(n+1, visited, sum+score[n][i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            score = new int[11][11];
            ans = Integer.MIN_VALUE;

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, new boolean[11], 0);

            System.out.println(ans);
        }

    }
}