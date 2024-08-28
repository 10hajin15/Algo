import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    static int[][] cctv = {{}, {1}, {1,3}, {0,1}, {0,1,3}, {0,1,2,3}};
    static int N, M, ans, CNT;
    static ArrayList<int[]> lst;

    static int cal(int[] tlst) {
        int[][] visited = new int[N+2][M+2];

        for (int i = 0; i < CNT; i++) {
            int si = lst.get(i)[0];
            int sj = lst.get(i)[1];
            int rot = tlst[i];
            int type = arr[si][sj];

            for(int dr: cctv[type]) {
                dr = (dr+rot) % 4;
                int ci = si, cj= sj;
                while (true) {
                    ci = ci+dirs[dr][0];
                    cj = cj+dirs[dr][1];
                    if(arr[ci][cj] == 6) break;
                    visited[ci][cj] = 1;
                }
            }
        }

        int cnt =0;
        for(int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                if(arr[i][j] == 0 && visited[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }

    static void dfs(int n, int[] tlst) {
        if(n==CNT) {
            ans = Math.min(ans, cal(tlst));
            return;
        }

        tlst[n] = 0;
        dfs(n+1, tlst);
        tlst[n] = 1;
        dfs(n+1, tlst);
        tlst[n] = 2;
        dfs(n+1, tlst);
        tlst[n] = 3;
        dfs(n+1, tlst);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = N*M;
        arr = new int[N+2][M+2];
        Arrays.fill(arr[0], 6);
        Arrays.fill(arr[N+1], 6);

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M+2; j++) {
                if(j==0 || j==M+1) {
                    arr[i][j] = 6;
                } else {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        lst = new ArrayList<>();

        for(int i=1; i<N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                if(0<arr[i][j] && arr[i][j]<6) {
                    lst.add(new int[] {i,j});
                }
            }
        }

        CNT = lst.size();
        int[] tmp = new int[CNT];
        dfs(0, tmp);
        System.out.println(ans);
    }
}