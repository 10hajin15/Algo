import java.io.*;
import java.util.*;

public class Main {
    static int[] parents;

    static int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) parents[py] = px;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        parents = new int[p];
        for (int i = 0; i < p; i++) parents[i] = i;

        List<Node> lst = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lst.add(new Node(a, b, cost));
        }

        lst.sort((n1, n2) -> n2.cost - n1.cost);

        for (Node node : lst) {
            union(node.from, node.to);
            if (find(c) == find(v)) {
                System.out.println(node.cost);
                return;
            }
        }
    }

    static class Node {
        int from, to, cost;

        Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}