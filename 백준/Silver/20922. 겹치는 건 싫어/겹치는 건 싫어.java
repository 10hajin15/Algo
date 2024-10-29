import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> count = new HashMap<>();

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int answer = 1;


        for (int end = 0; end < N; end++) {
            count.put(arr[end], count.getOrDefault(arr[end], 0) + 1);

            while(count.get(arr[end]) > K) {
                count.put(arr[start], count.get(arr[start])-1);
                if (count.get(arr[start]) == 0) {
                    count.remove(arr[start]);
                }
                start++;
            }

            answer = Math.max(answer, end - start + 1);
        }

        System.out.println(answer);
    }
}