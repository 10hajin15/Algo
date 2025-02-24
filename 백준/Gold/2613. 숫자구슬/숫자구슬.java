import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end += arr[i];
            start = Math.max(start, arr[i]);
        }

        while(true) {
            if(start > end) break;
            int mid = (start+end) / 2;

            int tmp = 0;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                tmp += arr[i];
                if(tmp > mid) {
                    cnt++;
                    tmp = arr[i];
                }
            }

            if(cnt > M) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        System.out.println(start);
        StringBuilder sb = new StringBuilder();
        int cnt = 0, sum = 0;
        int idx = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if(sum > start) {
                M--;
                cnt = (cnt == 0 ? 1 : cnt);
                sb.append(cnt).append(" ");
                sum = arr[i];
                cnt = 0;
            }
            cnt++;

            if(M == N-i) {
                idx = i;
                break;
            }
        }
        
        for (int i = idx; i < N; i++) {
            sb.append(cnt).append(" ");
            cnt = 1;
        }

        System.out.println(sb);
    }
}
