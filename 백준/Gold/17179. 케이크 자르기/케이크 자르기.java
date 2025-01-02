import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[M+1];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[M] = L;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            int left = 0;
            int right = L;

            while(left <= right) {
                int mid = (left+right) / 2;

                int cnt = 0;
                int prev = 0;
                for (int j = 0; j < M+1; j++) {
                    if(arr[j] - prev >= mid) {
                        cnt++;
                        prev = arr[j];
                    }
                }

                if(cnt >= num+1) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            sb.append(right).append('\n');
        }

        System.out.println(sb);
    }
}