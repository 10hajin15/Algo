import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;

    static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px < py) parents[py] = px;
        else parents[px] = py;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        List<Edge> lst = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            lst.add(new Edge(a, b, c));
        }

        Collections.sort(lst);

        int answer = 0;
        int edgeCount = 0;
        for (Edge now : lst) {
            if (find(now.to) != find(now.from)) {
                union(now.to, now.from);
                answer += now.cost;
                edgeCount++;
                if (edgeCount == V - 1) break;
            }
        }

        System.out.println(answer);
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}