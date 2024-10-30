import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;
        int answer = 0;
        int tmp = 0;
        while(true) {
            if(start > end) break;
            tmp = (end - start - 1) * Math.min(arr[start], arr[end]);
            answer = Math.max(answer, tmp);

            if(arr[start] < arr[end]) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(answer);
    }
}