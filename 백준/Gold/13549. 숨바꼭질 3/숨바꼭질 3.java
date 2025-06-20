import java.io.*;
import java.util.*;

public class Main {
    static int N, X, ans;

    static void bfs() {
        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});
        visited[N] = 0;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            if(now[0] == X) {
                ans = visited[X];
                return;
            }

            int ni = now[0] + 1;
            if(ni>=0 && ni<=100000) {
                if (visited[ni] > now[1]+1) {
                    q.add(new int[]{ni, now[1] + 1});
                    visited[ni] = now[1] + 1;
                }
            }

            ni = now[0] - 1;
            if(ni>=0 && ni<=100000) {
                if (visited[ni] > now[1]+1) {
                    q.add(new int[]{ni, now[1] + 1});
                    visited[ni] = now[1] + 1;
                }
            }

            ni = now[0] * 2;
            if(ni>=0 && ni<=100000) {
                if (visited[ni] > now[1]) {
                    q.add(new int[]{ni, now[1]});
                    visited[ni] = now[1];
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        ans = 0;

        bfs();

        System.out.println(ans);
    }
}