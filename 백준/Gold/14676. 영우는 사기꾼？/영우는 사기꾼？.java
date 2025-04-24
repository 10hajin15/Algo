import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] buildCount, indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        buildCount = new int[N + 1];
        indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            indegree[y]++;
        }

        boolean flag = false;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                if (indegree[b] > 0) {
                    flag = true;
                    break;
                }
                buildCount[b]++;
                if(buildCount[b] == 1) {
                    for(int nxt: graph[b]) {
                        indegree[nxt]--;
                    }
                }
            } else {
                if (buildCount[b] == 0) {
                    flag = true;
                    break;
                }
                buildCount[b]--;
                if(buildCount[b] == 0) {
                    for(int nxt: graph[b]) {
                        indegree[nxt]++;
                    }
                }
            }
        }

        System.out.println(flag ? "Lier!" : "King-God-Emperor");
    }
}