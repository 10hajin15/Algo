import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, H, D;
    static char[][] map;
    static ArrayList<int[]> point;
    static boolean[] visited;
    static int count;
    static int ans;
    static int si, sj, ei, ej;

    static void dfs(int di, int dj, int dist, int life, int u) {
        if(dist >= ans) return;

        if(di == ei && dj == ej) {
            ans = dist;
            return;
        }

        for(int i = 0; i < count; i++) {
            if(visited[i]) continue;

            int ni = point.get(i)[0];
            int nj = point.get(i)[1];

            int d = Math.abs(di - ni)+Math.abs(dj - nj);

            if(d-1 < life+u) {
                int h = life;
                if(u-d+1 < 0) h += (u-d+1);
                visited[i] = true;
                dfs(ni, nj, dist+d, h, D-1);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        point = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    si = i; sj = j;
                } else if(map[i][j] == 'E') {
                    ei = i; ej = j;
                } else if(map[i][j] == 'U') {
                    point.add(new int[] {i, j});
                }
            }
        }

        point.add(new int[] {ei, ej});
        count = point.size();

        visited = new boolean[count];
        ans = Integer.MAX_VALUE;

        dfs(si, sj, 0, H, 0);
        if(ans==Integer.MAX_VALUE) ans = -1;
        System.out.print(ans);
    }
}