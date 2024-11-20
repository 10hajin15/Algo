import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] cnt = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cnt[b]++;
            graph[a].add(b);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < N + 1; i++) {
            if (cnt[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");

            for (int nxt : graph[now]) {
                cnt[nxt]--;
                if (cnt[nxt] == 0) {
                    pq.add(nxt);
                }
            }
        }

        System.out.println(sb.toString());
    }
}