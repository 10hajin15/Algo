import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static List<int[]>[] graph;
    static List<int[]>[] reverseGraph;

    static int[] dijkstra(List<int[]>[] g, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> Integer.compare(o1[1], o2[1]));
        dist[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int cur = now[0];
            int cost = now[1];

            if (cost > dist[cur]) continue;

            for (int[] next : g[cur]) {
                int nxt = next[0];
                int time = next[1];

                if (dist[nxt] > cost + time) {
                    dist[nxt] = cost + time;
                    pq.add(new int[]{nxt, dist[nxt]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, time});
            reverseGraph[to].add(new int[]{from, time});
        }

        int[] go = dijkstra(graph, X);

        int[] back = dijkstra(reverseGraph, X);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, go[i] + back[i]);
        }

        System.out.println(max);
    }
}