import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] graph;
    static final int INF = 800000 * 2 + 1000;

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(dist[now.num] < now.cost) continue;

            for (Node nxt : graph[now.num]) {
                if (now.cost + nxt.cost < dist[nxt.num]) {
                    dist[nxt.num] = now.cost + nxt.cost;
                    pq.add(new Node(nxt.num, dist[nxt.num]));
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[v].add(new Node(w, c));
            graph[w].add(new Node(v, c));
        }

        st = new StringTokenizer(br.readLine());
        int d1 = Integer.parseInt(st.nextToken());
        int d2 = Integer.parseInt(st.nextToken());

        int path1 = dijkstra(1, d1) + dijkstra(d1, d2) + dijkstra(d2, N);
        int path2 = dijkstra(1, d2) + dijkstra(d2, d1) + dijkstra(d1, N);
        int result = Math.min(path1, path2);
        if(INF <= result) result = -1;

        System.out.println(result);
    }

    static class Node implements Comparable<Node> {
        int num, cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(cost, n.cost);
        }
    }
}