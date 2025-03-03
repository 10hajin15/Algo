import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N+1];
        List<Integer>[] graph = new ArrayList[N+1];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            graph[a].add(b);
        }

        int[] ans = new int[N+1];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 1; i < N+1; i++) {
            if(indegree[i] == 0) q.add(new int[] {i, 1});
        }

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int subject = now[0];
            int cnt = now[1];

            ans[subject] = cnt;

            for(int nxt: graph[subject]) {
                indegree[nxt]--;

                if(indegree[nxt] == 0) {
                    q.add(new int[]{nxt, cnt + 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb);
    }
}