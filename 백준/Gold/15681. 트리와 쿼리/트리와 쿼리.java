import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[] tree;
    static boolean[] visited;

    static int dfs(int root) {
        visited[root] = true;

        for(int i: graph[root]) {
            if(visited[i]) continue;
            tree[root] += dfs(i);
        }

        return tree[root];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new int[N+1];
        Arrays.fill(tree, 1);
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[N+1];
        dfs(R);

        for(int i=0; i<Q; i++) {
            int q = Integer.parseInt(br.readLine());
            System.out.println(tree[q]);
        }
    }
}