import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static Map<Integer, Integer> counts;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            int sizeA = counts.getOrDefault(pa, 1);
            int sizeB = counts.getOrDefault(pb, 1);

            if (pa < pb) {
                parent[pb] = pa;
                counts.put(pa, sizeA + sizeB);
                counts.remove(pb);
            } else {
                parent[pa] = pb;
                counts.put(pb, sizeA + sizeB);
                counts.remove(pa);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[1000001];
        counts = new HashMap<>();

        for (int i = 1; i <= 1000000; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();

            if (type.equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else {
                int c = Integer.parseInt(st.nextToken());
                int p = find(c);
                sb.append(counts.getOrDefault(p, 1)).append("\n");
            }
        }

        System.out.print(sb);
    }
}