import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i < N; i++) {
            int left = 0, right = N - 1;

            while(left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                long sum = arr[left] + arr[right];
                if (sum == arr[i]) {
                    count++;
                    break;
                }

                if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}