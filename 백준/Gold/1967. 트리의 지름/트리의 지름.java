import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Node>[] graph;
    static int[] dist;

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(visited[now.to]) continue;
            visited[now.to] = true;

            for (Node nxt : graph[now.to]) {
                if (now.cost + nxt.cost < dist[nxt.to]) {
                    dist[nxt.to] = now.cost + nxt.cost;
                    pq.add(new Node(nxt.to, dist[nxt.to]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra(1);

        int max = Integer.MIN_VALUE;
        int num = -1;
        for (int i = 1; i < N+1; i++) {
            if(max < dist[i]) {
                num = i;
                max = dist[i];
            }
        }

        dijkstra(num);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < N+1; i++) {
            if(answer < dist[i]) {
                answer = dist[i];
            }
        }

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.cost, node.cost);
        }
    }

}