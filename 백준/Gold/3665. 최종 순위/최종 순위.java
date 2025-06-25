import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        loop:
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] lastYear = new int[N + 1];
            int[][] graph = new int[N + 1][N + 1];
            int[] indegree = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                lastYear[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < N+1; i++) {
                int high = lastYear[i];
                for (int j = i+1; j < N+1; j++) {
                    int low = lastYear[j];
                    graph[high][low] = 1;
                    indegree[low]++;
                }
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(graph[a][b] == 1) {
                    graph[a][b] = 0;
                    graph[b][a] = 1;
                    indegree[a]++;
                    indegree[b]--;
                } else {
                    graph[a][b] = 1;
                    graph[b][a] = 0;
                    indegree[a]--;
                    indegree[b]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            List<Integer> answer = new ArrayList<>();

            for (int i = 1; i < N+1; i++) {
                if(indegree[i] == 0) q.add(i);
            }


            for (int i = 0; i < N; i++) {
                if(q.isEmpty()) {
                    sb.append("IMPOSSIBLE").append("\n");
                    continue loop;
                }

                if(q.size() > 1) {
                    sb.append("?").append("\n");
                    continue loop;
                }

                int now = q.poll();
                answer.add(now);

                for (int j = 1; j < N+1; j++) {
                    if(graph[now][j] == 1) {
                        indegree[j]--;
                        if(indegree[j] == 0) q.add(j);
                    }
                }
            }

            for (int ans: answer) {
                sb.append(ans).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}