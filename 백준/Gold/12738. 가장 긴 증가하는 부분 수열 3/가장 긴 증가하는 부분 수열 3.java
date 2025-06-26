import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int[] LIS = new int[N];
        LIS[0] = arr[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            int num = arr[i];

            if(LIS[count-1] < num) {
                LIS[count] = num;
                count++;
            } else {
                int left = 0;
                int right = count;

                while(left < right) {
                    int mid = (left + right) / 2;

                    if(LIS[mid] < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                LIS[left] = num;
            }
        }

        System.out.println(count);
    }
}