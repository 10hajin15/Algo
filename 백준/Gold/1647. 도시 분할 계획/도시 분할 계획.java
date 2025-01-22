import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    static int find(int x) {
        if(parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            parents[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Node> edges = new ArrayList<>();
        parents = new int[N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Node(u, v, w));
        }

        Collections.sort(edges);

        for(int i=1; i<N+1; i++) {
            parents[i] = i;
        }

        int result = 0;
        int last = 0;
        for(Node edge: edges) {
            if(find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                result += edge.w;
                last = edge.w;
            }
        }

        System.out.println(result - last);
    }

    static class Node implements Comparable<Node> {
        int u, v, w;

        public Node(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
}