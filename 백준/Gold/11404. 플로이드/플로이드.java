import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] graph;
    static int[][] dist;

    static void dijkstra(int start) {
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(visited[now.idx]) continue;
            visited[now.idx] = true;

            for(Node nxt: graph[now.idx]) {
                if(dist[start][nxt.idx] > dist[start][now.idx] + nxt.cost) {
                    dist[start][nxt.idx] = dist[start][now.idx] + nxt.cost;
                    pq.add(new Node(nxt.idx, dist[start][nxt.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
        }

        dist = new int[N+1][N+1];
        int INF = Integer.MAX_VALUE;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(i==j) continue;
                dist[i][j] = INF;
            }
        }

        for(int i=1; i<N+1; i++) {
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(dist[i][j] == INF) sb.append(0);
                else sb.append(dist[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
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