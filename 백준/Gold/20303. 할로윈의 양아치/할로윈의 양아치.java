import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] parent, size, candy;
    static List<int[]> groups = new ArrayList<>();
    static int[] dp;

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pb] = pa;
            size[pa] += size[pb];
            candy[pa] += candy[pb];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        candy = new int[N + 1];
        dp = new int[K];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            if (!visited[root]) {
                visited[root] = true;
                groups.add(new int[]{size[root], candy[root]});
            }
        }

        for (int[] group : groups) {
            int people = group[0];
            int totalCandy = group[1];

            for (int j = K - 1; j >= people; j--) {
                dp[j] = Math.max(dp[j], dp[j - people] + totalCandy);
            }
        }

        System.out.println(dp[K - 1]);
    }
}
