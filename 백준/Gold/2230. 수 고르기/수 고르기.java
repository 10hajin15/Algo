import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        int answer = Integer.MAX_VALUE;

        while (right < N) {
            int diff = arr[right] - arr[left];

            if (diff >= M) {
                answer = Math.min(answer, diff);
                left++;
            } else {
                right++;
            }
            
            if(left == right) right++;
        }

        System.out.println(answer);
    }
}