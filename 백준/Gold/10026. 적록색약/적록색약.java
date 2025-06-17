import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] arr;
    static int ans1, ans2;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static void bfs(int i, int j, boolean[][] visited, boolean isMan){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        char c = arr[i][j];

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int[] dir: dirs) {
                int ni = now[0] + dir[0];
                int nj = now[1] + dir[1];

                if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) continue;

                if(c == 'B') {
                    if(arr[ni][nj] == 'B') {
                        visited[ni][nj] = true;
                        q.add(new int[]{ni, nj});
                    }
                } else {
                    if(isMan) {
                        if(arr[ni][nj] == 'B') continue;
                        visited[ni][nj] = true;
                        q.add(new int[]{ni, nj});
                    } else {
                        if(c == arr[ni][nj]) {
                            visited[ni][nj] = true;
                            q.add(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited1 = new boolean[N][N];
        boolean[][] visited2 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited1[i][j]) {
                    bfs(i, j, visited1, false);
                    ans1++;
                }
                if(!visited2[i][j]) {
                    bfs(i, j, visited2, true);
                    ans2++;
                }
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}