import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] time = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer>[] graph = new ArrayList[N + 1];
            int[] indegree = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph[X].add(Y);
                indegree[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            int[] dp = new int[N + 1];
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                dp[i] = time[i];
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            while (!q.isEmpty()) {
                int now = q.poll(); 

                for (int next : graph[now]) {
                    dp[next] = Math.max(dp[next], dp[now] + time[next]);
                    indegree[next]--;

                    if (indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            sb.append(dp[W]).append("\n");
        }

        System.out.print(sb);
    }
}