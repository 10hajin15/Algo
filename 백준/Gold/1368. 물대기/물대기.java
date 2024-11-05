import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] wellCost;
    static ArrayList<Node> edges;
    static int[] parents;

    static int find(int x) {
        if(parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            parents[rootX] = rootY;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        wellCost = new int[N + 1];
        parents = new int[N + 1];
        edges = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            wellCost[i] = Integer.parseInt(br.readLine());
            parents[i] = i;
            edges.add(new Node(0, i, wellCost[i]));  // 우물 파는 비용을 가상의 노드와 연결
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i < j) {  // 간선 추가
                    edges.add(new Node(i, j, cost));
                }
            }
        }

        Collections.sort(edges);

        int totalCost = 0;
        for (Node edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalCost += edge.cost;
            }
        }

        System.out.println(totalCost);
    }

    static class Node implements Comparable<Node> {
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}