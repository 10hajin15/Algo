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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int s = 0;
        int cnt = 0;
        for(int e = 0; e < N; e++) {
            if(arr[e] % 2 != 0) cnt++;

            while(cnt > K) {
                if(arr[s] % 2 != 0) cnt--;
                s++;
            }

            ans = Math.max(ans, e - s + 1 - cnt);
        }

        System.out.println(ans);
    }
}