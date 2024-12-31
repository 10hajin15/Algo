import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        int left = 0;
        int right = sum;

        while (left <= right) {

            int mid = (left + right) / 2;

            int tmp = 0;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                tmp += arr[i];
                if (tmp >= mid) {
                    cnt++;
                    tmp = 0;
                }
            }

            if (cnt >= K) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
    }
}