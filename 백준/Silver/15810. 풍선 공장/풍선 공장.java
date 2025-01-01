import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        long t = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            t = Math.max(arr[i], t);
        }

        long left = 0;
        long right = t*M;

        while(left <= right) {
            long mid = (left + right) / 2;

            long tmp = 0;
            for (int i = 0; i < N; i++) {
                tmp += mid/arr[i];
            }


            if(tmp >= M) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.println(left);
    }
}