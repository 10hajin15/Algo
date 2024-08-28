import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] arr;

    static boolean dfs(int i, int cnt, boolean[] visited) {
        if(cnt >= 5) {
            return true;
        }
        visited[i] = true;
        for(int next : arr[i]) {
            if(!visited[next]) {
                if(dfs(next, cnt + 1, visited)) {
                    return true;
                }
            }
        }
        visited[i] = false;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            if(dfs(i, 1, visited)) {
                ans = 1;
                break;
            }
        }

        System.out.println(ans);
    }
}