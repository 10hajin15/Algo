import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        double sum = 0;

        for (int i = 0; i < N; i++) {
            sum += (double) arr[i][0] * arr[(i + 1) % N][1];
            sum -= (double) arr[i][1] * arr[(i + 1) % N][0];
        }

        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}