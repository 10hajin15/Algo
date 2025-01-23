import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] tree;
    static boolean[] visited;
    static int[][] dp;

    static void dfs(int node) {
        visited[node] = true;

        for(int nxt: tree[node]) {
            if(visited[nxt]) continue;

            dfs(nxt);

            dp[node][0] += dp[nxt][1];
            dp[node][1] += Math.min(dp[nxt][0], dp[nxt][1]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 1; i < N+1; i++) {
            tree[i] = new ArrayList<>();
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
}