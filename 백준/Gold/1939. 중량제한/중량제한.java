import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static long[] dist;
    static boolean[] visited;

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, Long.MAX_VALUE));
        dist[start] = Long.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.to] > now.cost) continue;

            for (Node next : graph[now.to]) {
                long minWeight = Math.min(now.cost, next.cost);

                if (dist[next.to] < minWeight) {
                    dist[next.to] = minWeight;
                    pq.add(new Node(next.to, minWeight));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        dist = new long[N + 1];
        graph = new List[N + 1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        
        System.out.println(dist[end]);
    }

    static class Node implements Comparable<Node> {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node e) {
            return Long.compare(-this.cost, -e.cost);
        }
    }
}