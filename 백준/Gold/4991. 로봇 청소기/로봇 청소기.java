import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int w, h;
    static char[][] arr;
    static ArrayList<int[]> dirty;
    static int[][] dist;
    static int size, ans;
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    static void dfs(int n, int start, boolean[] visited, int[] perm, int totalDist) {
        if(totalDist >= ans) return;

        if(n == size) {
            ans = Math.min(ans, totalDist);
            return;
        }

        for (int i = 1; i < size+1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                perm[n] = i;
                int next = dist[start][i];
                if(next != Integer.MAX_VALUE) {
                    dfs(n+1, i, visited, perm, totalDist+next);
                }
                visited[i] = false;
            }
        }
    }

    static int bfs(int si, int sj, int ti, int tj) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        q.add(new int[] {si, sj, 0});
        visited[si][sj] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            if(now[0] == ti && now[1] == tj) {
                return now[2];
            }

            for (int i = 0; i < 4; i++) {
                int ni = now[0] + dirs[i][0];
                int nj = now[1] + dirs[i][1];

                if(ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
                if(visited[ni][nj]) continue;
                if(arr[ni][nj] == 'x') continue;

                visited[ni][nj] = true;
                q.add(new int[] {ni, nj, now[2]+1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            arr = new char[h][w];
            dirty = new ArrayList<>();
            int robotX = -1, robotY = -1;

            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = str.charAt(j);
                    arr[i][j] = c;
                    if(c == 'o') {
                        robotX = i; robotY = j;
                        arr[i][j] = '.';
                    } else if(c == '*') {
                        dirty.add(new int[] {i, j});
                    }
                }
            }

            size = dirty.size();
            dist = new int[size+1][size+1];

            for (int i = 0; i < size+1 ; i++) {
                int[] start = (i==0) ? new int[] {robotX, robotY} : dirty.get(i-1);
                for (int j = 0; j < size+1; j++) {
                    if(i==j) continue;
                    int[] target = (j==0) ? new int[] {robotX, robotY} : dirty.get(j-1);
                    dist[i][j] = bfs(start[0], start[1], target[0], target[1]);
                    if(dist[i][j] == -1) dist[i][j] = Integer.MAX_VALUE;
                }
            }

            ans = Integer.MAX_VALUE;

            boolean[] visited = new boolean[size+1];
            int[] perm = new int[size];
            dfs(0, 0, visited, perm, 0);

            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }
}