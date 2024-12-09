import java.io.*;
import java.util.Arrays;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[] input;
    static boolean[] visited;
    static int N;

    static void dfs(int depth, char[] result) {
        if (depth == N) {
            sb.append(result).append("\n");
            return;
        }

        char prev = '\0';
        for (int i = 0; i < N; i++) {
            if (visited[i] || input[i] == prev) continue;

            visited[i] = true;
            result[depth] = input[i];
            prev = input[i];
            dfs(depth + 1, result);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String str = br.readLine();
            N = str.length();
            input = str.toCharArray();
            visited = new boolean[N];

            Arrays.sort(input);
            dfs(0, new char[N]);
        }

        System.out.print(sb.toString());
    }
}