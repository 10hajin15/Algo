import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int answer;
    static boolean[][] visited;

    static void dfs(int depth, int sum) {
        if(depth == (N*M)) {
            answer = Math.max(answer, sum);
            return;
        }

        int r = depth / M;
        int c = depth % M;

        if(visited[r][c]) {
            dfs(depth + 1, sum);
        } else {
            visited[r][c] = true;
            int num = arr[r][c];
            dfs(depth + 1, sum + arr[r][c]);

            int i;
            int tmp = num;
            for (i = r+1; i < N; i++) {
                if(visited[i][c]) break;
                visited[i][c] = true;
                tmp = (tmp * 10) + arr[i][c];
                dfs(depth + 1, sum + tmp);
            }

            for (int j = r+1; j < i; j++) {
                visited[j][c] = false;
            }

            tmp = num;
            for (i = c + 1; i < M; i++) {
                if(visited[r][i]) break;
                visited[r][i] = true;
                tmp = (tmp * 10) + arr[r][i];
                dfs(depth + i - c + 1, sum + tmp);
            }

            for (int j = c + 1; j < i; j++) {
                visited[r][j] = false;
            }

            visited[r][c] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }
}
