import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<List<Edge>> graph;
    static boolean[] flip;
    static boolean[] result;
    static int minFlip;

    static class Edge {
        int to, index;
        boolean dir;

        public Edge(int to, boolean dir, int index) {
            this.to = to;
            this.dir = dir;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, false, i));
            graph.get(v).add(new Edge(u, true, i));
        }

        flip = new boolean[N - 1];
        result = new boolean[N - 1];
        minFlip = Integer.MAX_VALUE;

        int flipCnt = init(1, 0);

        dfs(1, 0, flipCnt);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            sb.append(result[i] ? 1 : 0);
        }
        System.out.println(sb);
    }

    static int init(int node, int parent) {
        int sum = 0;
        for (Edge edge : graph.get(node)) {
            int next = edge.to;
            boolean dir = edge.dir;
            int idx = edge.index;
            if (next == parent) continue;

            flip[idx] = dir;
            sum += init(next, node) + (dir ? 1 : 0);
        }
        return sum;
    }

    static void dfs(int node, int parent, int flipCount) {
        if (flipCount < minFlip) {
            minFlip = flipCount;
            for (int i = 0; i < N - 1; i++) {
                result[i] = flip[i];
            }
        }

        for (Edge edge : graph.get(node)) {
            int next = edge.to;
            boolean dir = edge.dir;
            int idx = edge.index;
            if (next == parent) continue;

            if (dir) {
                flip[idx] = false;
                dfs(next, node, flipCount - 1);
                flip[idx] = true;
            } else {
                flip[idx] = true;
                dfs(next, node, flipCount + 1);
                flip[idx] = false;
            }
        }
    }
}