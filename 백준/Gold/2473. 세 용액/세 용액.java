import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long sum = Long.MAX_VALUE;
        long[] ans = new long[3];
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N-1;

            while(left < right) {
                if(left == i) {
                    left++;
                    continue;
                }

                if(right == i) {
                    right--;
                    continue;
                }
                long tmp = arr[i] + arr[left] + arr[right];

                if(Math.abs(tmp) < Math.abs(sum)) {
                    sum = Math.abs(tmp);
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                }

                if(tmp == 0) {
                    sum = 0;
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                    break;
                } else if(tmp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        Arrays.sort(ans);

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}