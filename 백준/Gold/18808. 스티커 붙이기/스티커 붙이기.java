import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N, M;
    static int R, C;

    public static boolean checkSticker(int di, int dj, int[][] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                if (tmp[i][j] == 1) {
                    int ni = i + di;
                    int nj = j + dj;
                    if (ni < 0 || ni >= N || nj < 0 || nj >= M || arr[ni][nj] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int[][] rotateSticker(int[][] tmp, int rotate) {
        int row = tmp.length;
        int col = tmp[0].length;
        int[][] rotatedSticker;

        if (rotate == 1 || rotate == 3) {
            rotatedSticker = new int[col][row];
        } else {
            rotatedSticker = new int[row][col];
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (rotate) {
                    case 1:
                        rotatedSticker[j][row - 1 - i] = tmp[i][j];
                        break;
                    case 2:
                        rotatedSticker[row - 1 - i][col - 1 - j] = tmp[i][j];
                        break;
                    case 3:
                        rotatedSticker[col - 1 - j][i] = tmp[i][j];
                        break;
                    default:
                        rotatedSticker[i][j] = tmp[i][j];
                }
            }
        }

        return rotatedSticker;
    }

    public static boolean putSticker(int[][] tmp) {
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i <= N - tmp.length; i++) {
                for (int j = 0; j <= M - tmp[0].length; j++) {
                    if (checkSticker(i, j, tmp)) {
                        for (int x = 0; x < tmp.length; x++) {
                            for (int y = 0; y < tmp[0].length; y++) {
                                if (tmp[x][y] == 1) {
                                    arr[i + x][j + y] = 1;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
            tmp = rotateSticker(tmp, 1);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            int[][] tmp = new int[R][C];

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    tmp[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            putSticker(tmp);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) answer++;
            }
        }

        System.out.println(answer);
    }
}