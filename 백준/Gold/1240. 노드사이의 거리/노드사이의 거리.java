import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Node>[] graph;
    static int N;

    static int query(int a, int b) {
        boolean[] visited = new boolean[N+1];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(a, 0));
        visited[a] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.idx == b) {
                return now.cost;
            }

            for(Node nxt: graph[now.idx]) {
                if(visited[nxt.idx]) continue;
                visited[nxt.idx] = true;
                q.add(new Node(nxt.idx, now.cost + nxt.cost));
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(query(a, b));
        }
    }

    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}