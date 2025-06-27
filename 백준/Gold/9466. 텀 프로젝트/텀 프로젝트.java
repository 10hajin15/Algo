import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] arr;
    static int count;
    static boolean[] visited;
    static boolean[] finished;

    static void dfs(int n) {
        visited[n] = true;

        int nxt = arr[n];

        if (!visited[nxt]) {
            dfs(nxt);
        } else if(!finished[nxt]) {
            int tmp = nxt;
            while(tmp != n) {
                tmp = arr[tmp];
                count++;
            }
            count++;
        }

        finished[n] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            arr = new int[N + 1];
            count = 0;
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < N+1; i++) {
                if(!visited[i]) dfs(i);
            }

            sb.append(N - count).append("\n");
        }
        System.out.println(sb.toString());
    }
}