import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] graph;
    static boolean[] visited, isCycle;
    static int cycleStart = -1;
    static boolean foundCycle = false;
    static int[] dist;

    static boolean dfs(int cur, int parent) {
        visited[cur] = true;

        for (int nxt : graph[cur]) {
            if (nxt == parent) continue;

            if (!visited[nxt]) {
                if (dfs(nxt, cur)) {
                    if (!foundCycle) {
                        isCycle[cur] = true;
                        if (cur == cycleStart) {
                            foundCycle = true;
                        }
                    }
                    return true;
                }
            } else {
                cycleStart = nxt;
                isCycle[cur] = true;
                return true;
            }
        }
        return false;
    }

    static void bfs() {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();

        for(int i=1;i<=N;i++){
            if(isCycle[i]){
                dist[i] = 0;
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nxt : graph[cur]){
                if(dist[nxt] == -1){
                    dist[nxt] = dist[cur] + 1;
                    q.add(nxt);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        isCycle = new boolean[N+1];
        graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[v].add(w);
            graph[w].add(v);
        }

        dfs(1, -1);
        dist = new int[N+1];
        bfs();

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++){
            sb.append(dist[i]).append(' ');
        }
        System.out.println(sb);
    }
}