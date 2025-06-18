import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, r, c, ans;

    static void dfs(int i, int j, int size) {
        if(size == 1) {
            return;
        }

        int newSize = size / 2;

        if (r < i + newSize && c < j + newSize) {
            dfs(i, j, newSize);
        } else if (r < i + newSize && j + newSize <= c) {
            ans += (size * size) / 4;
            dfs(i, j + newSize, newSize);
        } else if (i + newSize <= r && c < j + newSize) {
            ans += ((size * size) / 4) * 2;
            dfs(i + newSize, j, newSize);
        } else if (i + newSize <= r && j + newSize <= c) {
            ans += ((size * size) / 4) * 3;
            dfs(i + newSize, j + newSize, newSize);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        ans = 0;

        dfs(0, 0, (int)Math.pow(2, N));

        System.out.println(ans);
    }
}