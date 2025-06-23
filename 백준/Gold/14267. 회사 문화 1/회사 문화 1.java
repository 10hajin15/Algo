import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] tree;
    static int[] score;

    static void dfs(int n) {
        for (int nxt: tree[n]) {
            score[nxt] += score[n];
            dfs(nxt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];

        for (int i = 1; i < N+1; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(x == -1) continue;
            tree[x].add(i);
        }

        score = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            score[x] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) {
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}