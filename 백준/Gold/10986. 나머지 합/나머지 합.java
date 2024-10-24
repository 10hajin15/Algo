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
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;
        long answer = 0;

        long[] modCnt = new long[M];
        modCnt[0] = 1;

        for (int i = 0; i < N; i++) {
            sum += arr[i];

            int mod = (int)(sum%M);

            answer += modCnt[mod];

            modCnt[mod]++;
        }

        System.out.println(answer);
    }
}