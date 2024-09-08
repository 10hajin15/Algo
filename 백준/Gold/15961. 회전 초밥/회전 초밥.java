import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+k-1];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<k-1; i++) {
            arr[N+i] = arr[i];
        }

        int[] eat = new int[d+1];
        eat[c] += 1;
        int ans = 1;

        for(int i=0; i<k; i++) {
            if(eat[arr[i]] == 0) ans += 1;
            eat[arr[i]] += 1;
        }

        int start = 0;
        int end = k;
        int tmp = ans;

        for(int i=end; i<(N+k-1); i++) {
            eat[arr[start]] -= 1;
            if(eat[arr[start]] == 0) tmp -= 1;

            if(eat[arr[i]] == 0) tmp += 1;
            eat[arr[i]] += 1;

            ans = Math.max(tmp, ans);
            start++;
        }

        System.out.println(ans);
    }
}