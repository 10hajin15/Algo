import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, W;
    static List<Node>[] graph;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    static boolean bellmanford(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i <= N; i++) {
            boolean updated = false;
            for (int j = 0; j <= N; j++) {
                for (Node node : graph[j]) {
                    int v = node.to;
                    int cost = node.cost;

                    if (dist[j] != INF && dist[v] > dist[j] + cost) {
                        dist[v] = dist[j] + cost;
                        updated = true;
                        if (i == N) return true;
                    }
                }
            }

            if (!updated) break;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            dist = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph[s].add(new Node(e, t));
                graph[e].add(new Node(s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                graph[s].add(new Node(e, -t));
            }

            for (int i = 1; i <= N; i++) {
                graph[0].add(new Node(i, 0));
            }

            boolean hasCycle = bellmanford(0);
            sb.append(hasCycle ? "YES\n" : "NO\n");
        }

        System.out.print(sb.toString());
    }

    static class Node {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}