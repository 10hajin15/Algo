import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];

            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers);

            boolean flag = true;

            for (int i = 0; i < n - 1; i++) {
                if (numbers[i+1].startsWith(numbers[i])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb.toString());
    }
}