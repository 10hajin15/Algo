import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dist, dp;
    static List<int[]>[] graph;
    static final int INF = Integer.MAX_VALUE;

    static void dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        dist[start] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int n = now[0];
            int cost = now[1];

            for (int[] g: graph[n]) {
                if(visited[g[0]]) continue;
                if(dist[g[0]] > cost+g[1]) {
                    dist[g[0]] = cost+g[1];
                    q.add(new int[]{g[0], dist[g[0]]});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        dp = new int[N + 1];
        graph = new ArrayList[N + 1];

        Arrays.fill(dist, INF);
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        dijkstra(2);

        dp[2] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> Integer.compare(a[1], b[1]));

        for (int i = 1; i < N+1; i++) {
            if(i == 2) continue;
            pq.add(new int[]{i, dist[i]});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int n = now[0];

            for(int[] g: graph[n]) {
                int nxt = g[0];
                if(dist[nxt] < dist[n]) dp[n] += dp[nxt];
            }
        }

        System.out.println(dp[1]);
    }
}