import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Node>[] graph;
    static int N;
    static int[] dist;

    static void getDist(int start) {
        dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Node> q = new LinkedList<>();
        dist[start] = 0;
        visited[start] = true;
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node nxt : graph[now.idx]) {
                if (visited[nxt.idx]) continue;
                if (dist[nxt.idx] > now.cost + nxt.cost) {
                    visited[nxt.idx] = true;
                    dist[nxt.idx] = now.cost + nxt.cost;
                    q.add(new Node(nxt.idx, dist[nxt.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            while (true) {
                int idx = Integer.parseInt(st.nextToken());
                if (idx == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                graph[num].add(new Node(idx, cost));
            }
        }


        getDist(1);

        int maxStart = 1;
        for (int i = 2; i < N + 1; i++) {
            if (dist[maxStart] < dist[i]) {
                maxStart = i;
            }
        }

        getDist(maxStart);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] > answer) answer = dist[i];
        }

        System.out.println(answer);
    }

    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}