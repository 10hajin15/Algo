import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static char[][] arr;
    static int[][] bomb;
    static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static int N;

    static boolean isBomb(int i, int j) {
        for(int x=0; x<8; x++) {
            int nx = i+dirs[x][0];
            int ny = j+dirs[x][1];

            if(0<=nx && nx<N && 0<=ny && ny<N) {
                if (arr[nx][ny] == '*') return true;
            }
        }
        return false;
    }

    static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        bomb[i][j] = -1;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int x=0; x<8; x++) {
                int nx = now[0]+dirs[x][0];
                int ny = now[1]+dirs[x][1];

                if(0<=nx && nx<N && 0<=ny && ny<N) {
                    if(bomb[nx][ny] == 1) {
                        q.offer(new int[]{nx, ny});
                    }
                    bomb[nx][ny] = -1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int ans = 0;
            N = Integer.parseInt(br.readLine());
            arr = new char[N][N];

            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<N; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            bomb = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(arr[i][j] == '.' && !isBomb(i, j)) {
                        bomb[i][j] = 1;
                    }
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(bomb[i][j] == 1) {
                        ans += 1;
                        bfs(i, j);
                    }
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(bomb[i][j] != -1 && arr[i][j] == '.') {
                        ans += 1;
                    }
                }
            }

            System.out.println("#"+tc+" "+ans);
        }
    }
}