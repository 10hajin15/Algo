import java.io.*;
import java.util.*;

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

        int N = Integer.parseInt(br.readLine());

        ArrayList<Node> edges = new ArrayList<>();
        parents = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int w = Integer.parseInt(st.nextToken());
                if(j <= i) continue;
                edges.add(new Node(i, j, w));
            }
        }

        Collections.sort(edges);

        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        long result = 0;
        int count = 0;
        for(Node edge: edges) {
            if(find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                result += edge.w;
                count++;
                if(count == N-1) break;
            }
        }

        System.out.println(result);
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
