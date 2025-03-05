import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[1000001];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (; start + 1 <= end; start++) {
                arr[start]++;
            }
        }

        int left = 0, right = 0, sum = 0;

        while (left <= right && right <= 1000000) {
            if (sum == k) {
                System.out.println(left + " " + right);
                return;
            } else if (sum < k) {
                sum += arr[right++];
            } else {
                sum -= arr[left++];
            }
        }

        System.out.println("0 0");
    }
}
