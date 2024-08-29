import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static int[] parents;

    static int findSet(int a) {
        if (a != parents[a]) {
            parents[a] = findSet(parents[a]);
        }
        return parents[a];
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot == bRoot) return false;

        if(aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        Arrays.sort(edges);
        parents = new int[V];
        for(int i=0; i<V; i++) {
            parents[i] = i;
        }

        int cnt=0, cost=0;
        for(Edge edge: edges) {
            if(union(edge.start, edge.end)) {
                cost += edge.weight;
                if(++cnt == V-1) break;
            }
        }

        System.out.println(cost);
    }

    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}