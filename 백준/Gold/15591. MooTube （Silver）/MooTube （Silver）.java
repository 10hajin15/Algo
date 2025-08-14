import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[N+1];
        for(int i=1;i<N+1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int cnt = 0;
            boolean[] visited = new boolean[N+1];
            Queue<Edge> q = new LinkedList<>();
            q.add(new Edge(v, 0));
            visited[v] = true;

            while(!q.isEmpty()) {
                Edge now = q.poll();

                for(Edge nxt: graph[now.to]) {
                    if(!visited[nxt.to] && nxt.weight >= k) {
                        visited[nxt.to] = true;
                        q.add(new Edge(nxt.to, nxt.weight));
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.println(sb.toString());
    }

    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
}