import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static boolean[] vParty;
    static List<Integer>[] graph;
    static List<Integer>[] party;

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int par : graph[now]) {
                if(vParty[par]) continue;
                for (int nxt : party[par]) {
                    if(visited[nxt]) continue;
                    visited[nxt] = true;
                    q.add(nxt);
                }
                vParty[par] = true;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        vParty = new boolean[M];
        graph = new ArrayList[N + 1];
        party = new ArrayList[M];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> facts = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(st.nextToken());
            facts.add(n);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            for (int j = 0; j < k; j++) {
                int n = Integer.parseInt(st.nextToken());
                party[i].add(n);
                graph[n].add(i);
            }
        }

        for (int fact : facts) {
            if(!visited[fact]) bfs(fact);
        }

        int answer = 0;
        for (List<Integer> par : party) {
            boolean flag = false;
            for (int p : par) {
                if(visited[p]) {
                    flag = true;
                    break;
                }
            }
            if(!flag) answer++;
        }

        System.out.println(answer);
    }
}