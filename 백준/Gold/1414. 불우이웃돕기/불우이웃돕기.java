import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] parents;

    static int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parents[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String alph = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Node> edges = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                int cost = alph.indexOf(str.charAt(j));
                if (cost == 0) continue;
                total += cost;
                if (i == j) continue;
                edges.add(new Node(i, j, cost));
            }
        }

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        Collections.sort(edges);
        int result = 0;
        int edgeCount = 0;

        for (Node edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                result += edge.w;
                edgeCount++;
            }
        }

        int answer = 0;
        if (edgeCount == N - 1) {
            answer = total - result;
        } else {
            answer = -1;
        }

        System.out.println(answer);
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