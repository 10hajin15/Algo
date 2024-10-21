import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+2][N+2];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 2; j < N+1; j++) {
                arr[i][j] += arr[i][j-1];
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int pi = Integer.parseInt(st.nextToken());
            int pj = Integer.parseInt(st.nextToken());
            int ni = Integer.parseInt(st.nextToken());
            int nj = Integer.parseInt(st.nextToken());

            int ans = 0;
            for (int i = pi; i <= ni ; i++) {
                ans += (arr[i][nj] - arr[i][pj-1]);
            }
            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}