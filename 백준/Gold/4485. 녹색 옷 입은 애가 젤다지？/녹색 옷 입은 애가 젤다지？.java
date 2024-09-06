import java.util.*;
import java.io.*;

public class Main {
    static Node[][] arr;
    static int N;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    static int ans;
    static int[][] dist;

    static void dijkstra() {
        int INF = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(arr[0][0]);
        dist[0][0] = arr[0][0].cost;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int di = now.x;
            int dj = now.y;

            for(int i=0; i<4; i++) {
                int ni = di + dirs[i][0];
                int nj = dj + dirs[i][1];

                if(ni<0 || ni>=N || nj<0 || nj>=N) continue;

                if(dist[ni][nj] > dist[di][dj] + arr[ni][nj].cost) {
                    dist[ni][nj] = dist[di][dj] + arr[ni][nj].cost;
                    pq.offer(arr[ni][nj]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N==0) {
                bw.write(sb.toString());
                bw.flush();
                bw.close();
                br.close();
                break;
            }

            arr = new Node[N][N];
            ans = Integer.MAX_VALUE;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    int cost = Integer.parseInt(st.nextToken());
                    Node newNode = new Node(i, j, cost);
                    arr[i][j] = newNode;
                }
            }

            dist = new int[N][N];
            dijkstra();

            sb.append("Problem ").append(tc).append(": ").append(dist[N-1][N-1]).append("\n");
            tc++;
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
}