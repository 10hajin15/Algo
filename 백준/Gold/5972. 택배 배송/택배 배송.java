import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void dijkstra(int start) {
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().idx;

            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Node nxt: graph[nowVertex]) {
                if(dist[nxt.idx] > dist[nowVertex]+nxt.cost) {
                    dist[nxt.idx] = dist[nowVertex]+nxt.cost;

                    pq.offer(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        dijkstra(1);

        System.out.println(dist[N]);
    }

    static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}