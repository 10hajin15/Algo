import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<int[]>[] graph = new ArrayList[N + 1];
        int[] indegree = new int[N + 1];
        int[] ans = new int[N + 1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());

            graph[X].add(new int[] {Y, Z});
            indegree[Y]++;
        }


        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N+1 ; i++) {
            if(indegree[i] == 0) {
                q.add(i);
                ans[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for(int[] nxt: graph[now]) {
                int n = nxt[0];
                int cnt = nxt[1];

                ans[n] += ans[now] * cnt;

                indegree[n]--;

                if(indegree[n] == 0) q.add(n);
            }
        }

        for (int i = 1; i < N+1; i++) {
            if (graph[i].isEmpty()) {
                sb.append(i).append(" ").append(ans[i]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
