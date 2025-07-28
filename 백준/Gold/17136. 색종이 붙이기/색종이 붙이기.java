import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5,};
    static int answer = Integer.MAX_VALUE;

    static void dfs(int x, int y, int count) {
        if (x >= 9 && y > 9) {
            answer = Math.min(answer, count);
            return;
        }

        if(count >= answer) return;

        if(y > 9) {
            dfs(x + 1, 0, count);
            return;
        }

        if (arr[x][y] == 1) {
            for (int size = 5; size > 0; size--) {
                if (paper[size] > 0 && canAttach(x, y, size)) {
                    attach(x, y, size, 0);
                    paper[size]--;
                    dfs(x, y + 1, count + 1);
                    paper[size]++;
                    attach(x, y, size, 1);
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if(arr[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void attach(int x, int y, int size, int num) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arr[i][j] = num;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}