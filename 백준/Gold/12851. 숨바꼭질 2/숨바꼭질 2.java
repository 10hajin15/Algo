import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visited = new int[MAX + 1];
        Arrays.fill(visited, -1);

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});

        int count = 0;
        int time = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int pos = now[0];
            int t = now[1];

            if (visited[pos] != -1 && visited[pos] < t) continue;
            visited[pos] = t;

            if (pos == K) {
                if (t < time) {
                    time = t;
                    count = 1;
                } else if (t == time) {
                    count++;
                }
                continue;
            }

            int[] nexts = {pos - 1, pos + 1, pos * 2};
            for (int next : nexts) {
                if (next < 0 || next > MAX) continue;
                if (visited[next] == -1 || visited[next] >= t + 1) {
                    q.add(new int[]{next, t + 1});
                }
            }
        }

        System.out.println(time);
        System.out.println(count);
    }
}