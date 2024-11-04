import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<int[]> edges = new ArrayList<>();
        parent = new int[N+1];
        ArrayList<Integer> station = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            station.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new int[] {w, u, v});
        }

        edges.sort((int[] o1, int[] o2) -> Integer.compare(o1[0], o2[0]));

        for(int i=1; i<N+1; i++) {
            if(station.contains(i)) continue;
            parent[i] = i;
        }

        int result = 0;
        for(int[] edge: edges) {
            if(find(edge[1]) != find(edge[2])) {
                union(edge[1], edge[2]);
                result += edge[0];
            }
        }

        System.out.println(result);
    }
}