import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = Integer.MAX_VALUE;
        int end = 0;
        int sum = 0;
        for (int start = 0; start < N; start++) {
            while(sum < S) {
                if(end >= N) break;
                sum += arr[end];
                end++;
            }
            if(sum >= S) {
                len = Math.min(len, end-start);
                sum -= arr[start];
            }
        }

        if(len == Integer.MAX_VALUE) {
            len = 0;
        }

        System.out.println(len);
    }
}
