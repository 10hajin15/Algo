import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, T, M, L;
    static List<Node>[] graph;
    static int answer;

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        pq.add(new Node(start, 0, 0));
        visited[start] = true;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.to == N) {
                answer = now.cost;
                return;
            }

            for (Node nxt : graph[now.to]) {
                if(visited[nxt.to]) continue;
                if (now.time + nxt.time <= T && now.cost + nxt.cost <= M) {
                    pq.add(new Node(nxt.to, now.time + nxt.time, now.cost + nxt.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        L = Integer.parseInt(br.readLine());

        graph = new List[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, t, c));
            graph[b].add(new Node(a, t, c));
        }

        dijkstra(1);

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int to, time, cost;

        Node(int to, int time, int cost) {
            this.to = to;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }
}