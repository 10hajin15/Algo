import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] arr = new int[R][C];

        int[] air = new int[2];
        int idx = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == -1) {
                    air[idx++] = i;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            int[][] tmp = new int[R][C];
            tmp[air[0]][0] = tmp[air[1]][0] = -1;

            for (int x = 0; x < R; x++) {
                for (int y = 0; y < C; y++) {
                    if(arr[x][y] > 0) {
                        int cnt = 0;
                        int add = arr[x][y] / 5;
                        for(int[] dir: dirs) {
                            int nx = x + dir[0];
                            int ny = y + dir[1];

                            if(nx<0 || nx>=R || ny<0 || ny>=C) continue;
                            if(arr[nx][ny] == -1) continue;

                            cnt++;
                            tmp[nx][ny] += add;
                        }

                        tmp[x][y] += arr[x][y] - (add * cnt);
                    }
                }
            }

            arr = tmp;
            tmp[air[0]][0] = tmp[air[1]][0] = -1;

            tmp = new int[R][C];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (r==0) {
                        if(c==C-1) {
                            tmp[r][c] = arr[r+1][c];
                        } else {
                            tmp[r][c] = arr[r][c + 1];
                        }
                    } else if(r == air[0] || r == air[1]) {
                        if (c < 2) continue;
                        else {
                            tmp[r][c] = arr[r][c - 1];
                        }
                    } else if(r == R-1) {
                        if(c == C-1) {
                            tmp[r][c] = arr[r - 1][c];
                        } else {
                            tmp[r][c] = arr[r][c + 1];
                        }
                    } else if(c==0) {
                        if(r < air[0]) {
                            tmp[r][c] = arr[r - 1][c];
                        } else if(r > air[1]) {
                            tmp[r][c] = arr[r + 1][c];
                        }
                    } else if(c == C-1) {
                        if(r < air[0]) {
                            tmp[r][c] = arr[r + 1][c];
                        } else if(r > air[1]) {
                            tmp[r][c] = arr[r - 1][c];
                        }
                    } else {
                        tmp[r][c] = arr[r][c];
                    }
                }
            }

            arr = tmp;
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(arr[i][j] > 0) {
                    answer += arr[i][j];
                }
            }
        }

        System.out.println(answer);
    }
}