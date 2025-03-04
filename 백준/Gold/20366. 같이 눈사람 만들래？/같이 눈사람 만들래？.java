import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                int snowman1 = arr[i] + arr[j];

                int left = 0, right = N-1;

                while(left < right) {
                    if(left == i || left == j) {
                        left++;
                        continue;
                    }

                    if(right == i || right == j) {
                        right--;
                        continue;
                    }

                    int snowman2 = arr[left] + arr[right];
                    answer = Math.min(answer, Math.abs(snowman1 - snowman2));

                    if(snowman1 > snowman2) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}