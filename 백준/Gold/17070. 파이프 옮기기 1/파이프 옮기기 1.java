import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        Matrix[][] dp = new Matrix[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = new Matrix(0, 0, 0);
            }
        }

        dp[0][0].row = 1;
        dp[0][1].row = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if(dp[i][j].row > 0) {
                    if(j+1 < N && arr[i][j+1] != 1) {
                        dp[i][j+1].row += dp[i][j].row;

                        if(i+1 < N && arr[i+1][j] != 1 && arr[i+1][j+1] != 1) {
                            dp[i+1][j+1].dia += dp[i][j].row;
                        }
                    }
                }

                if(dp[i][j].col > 0) {
                    if(i+1 < N && arr[i+1][j] != 1) {
                        dp[i+1][j].col += dp[i][j].col;

                        if(j+1 < N && arr[i][j+1] != 1 && arr[i+1][j+1] != 1) {
                            dp[i+1][j+1].dia += dp[i][j].col;
                        }
                    }
                }

                if (dp[i][j].dia > 0) {
                    if(j+1 < N && arr[i][j+1] != 1) dp[i][j+1].row += dp[i][j].dia;
                    if(i+1 < N && arr[i+1][j] != 1) dp[i+1][j].col += dp[i][j].dia;
                    if(i+1 < N && j+1 < N && arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1)
                        dp[i + 1][j + 1].dia += dp[i][j].dia;
                }
            }
        }

        int answer = dp[N-1][N-1].row + dp[N-1][N-1].col + dp[N-1][N-1].dia;

        System.out.println(answer);
    }

    static class Matrix {
        int row, col, dia;

        Matrix(int row, int col, int dia) {
            this.row = row;
            this.col = col;
            this.dia = dia;
        }
    }
}