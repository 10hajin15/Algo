import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;
        long[] ans = new long[]{arr[start], arr[end]};
        long sum = arr[start] + arr[end];

        while(true) {
            if(start >= end) break;

            long tmp = arr[start]+arr[end];

            if (Math.abs(tmp) < Math.abs(sum)) {
                sum = tmp;
                ans[0] = arr[start];
                ans[1] = arr[end];
            }

            if (tmp == 0) {
                break;
            }
            else if (tmp > 0) {
                end--;
            }
            else {
                start++;
            }
        }

        System.out.println(ans[0]+" "+ans[1]);
    }
}