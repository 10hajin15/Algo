import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, ans;
    static boolean[][] visited;
    static int[][] dirs = {{-1,1},{0,1},{1,1}};
    static String[] arr;

    static boolean dfs(int n, int x) {
        if(n >= C-2) {
            ans+=1;
            return true;
        }

        for(int i=0; i<3; i++) {
            int nx = x+dirs[i][0];
            int ny = n+dirs[i][1];
            if(0<=nx && nx<R && 0<=ny && ny<C) {
                if(arr[nx].charAt(ny) == '.' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (dfs(n+1, nx)) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        arr = new String[R];

        for(int i=0; i<R; i++) {
            arr[i] = br.readLine();
        }

        for(int x=0; x<R; x++) {
            visited[x][0] = true;
            dfs(0, x);
        }

        System.out.println(ans);
    }
}