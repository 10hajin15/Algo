import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long left = 1, right = 30 * N;
        long time = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            long cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += (mid / arr[i]);
            }

            if (cnt < N) {
                left = mid + 1;
            } else {
                time = mid;
                right = mid - 1;
            }
        }

        long count = M;
        for (int i = 0; i < M; i++) {
            count += (time - 1) / arr[i];
        }

        for (int i = 0; i < M; i++) {
            if (time % arr[i] == 0) count++;

            if (count == N) {
                System.out.println(i + 1);
                return;
            }
        }
    }
}
