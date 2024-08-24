import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int index;
    int cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

public class Main {
    static int V, E;
    static ArrayList<Node>[] graph;

    public static void Dijkstra(int start) {
        boolean[] visited = new boolean[V+1];
        int[] dist = new int[V+1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int now = pq.poll().index;

            if(visited[now]) continue;
            visited[now] = true;

            for(Node next: graph[now]) {
                if(dist[next.index] > dist[now]+next.cost) {
                    dist[next.index] = dist[now]+next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        for(int i=1; i<V+1; i++) {
            if(dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V+1];

        for(int i=0; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        Dijkstra(start);
    }
}