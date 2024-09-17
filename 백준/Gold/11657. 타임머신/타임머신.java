import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Node> graph;
    static long INF = Integer.MAX_VALUE;
    static long[] dist;

    static boolean BellmanFord(int start) {
        dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node cur = graph.get(j);

                if(dist[cur.v] != INF && dist[cur.w] > dist[cur.v] + cur.cost) {
                    dist[cur.w] = dist[cur.v] + cur.cost;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            Node cur = graph.get(i);

            if(dist[cur.v] != INF && dist[cur.w] > dist[cur.v] + cur.cost) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Node(v, w, cost));
        }

        boolean flag = BellmanFord(1);

        StringBuilder sb = new StringBuilder();
        if(flag) {
            sb.append("-1\n");
        } else {
            for (int i = 2; i < N+1; i++) {
                if(dist[i] == INF) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i]+"\n");
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        int v, w, cost;

        public Node(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
}