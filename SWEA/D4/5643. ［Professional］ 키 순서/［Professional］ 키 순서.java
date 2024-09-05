import java.io.*;
import java.util.*;

public class Solution {
    static int[] in, out;
    static int N;
    static List<Integer>[] graph;

    static void bfs(int start) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int v : graph[cur]) {
                if(!visited[v]) {
                    out[start]++;
                    in[v]++;
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            in = new int[N+1];
            out = new int[N+1];
            graph = new ArrayList[N+1];

            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
            }

            for(int i=1; i<=N; i++) {
                bfs(i);
            }

            int ans = 0;
            for (int i = 1; i <= N; i++) {
                if(in[i]+out[i]==(N-1)) ans++;
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}