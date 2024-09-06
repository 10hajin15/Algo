import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N;
    static int ans;

    static void dfs(int n, int si, int start, int cost, boolean[] visited) {
        if(n == N-1) {
            if(arr[start][si] == 0) return;
            cost += arr[start][si];
            if(ans > cost) ans = cost;
            return;
        }

        for(int i=0; i<N; i++) {
            if (arr[start][i] != 0 && !visited[i]) {
                visited[i] = true;
                dfs(n+1, si, i, cost+arr[start][i], visited);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(0, i, i, 0, visited);
        }


        System.out.println(ans);
    }
}