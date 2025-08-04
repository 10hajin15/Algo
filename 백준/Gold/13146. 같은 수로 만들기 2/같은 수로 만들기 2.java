import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        long max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            if (arr[i] > max) max = arr[i];
        }

        long count = 0;
        Stack<Long> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            long now = arr[i];
            while (!stack.isEmpty() && stack.peek() < now) {
                long mid = stack.pop();
                long left = stack.isEmpty() ? now : Math.min(stack.peek(), now);
                count += left - mid;
            }
            if (stack.isEmpty() || stack.peek() > now) {
                stack.push(now);
            }
        }

        while (stack.size() > 1) {
            long top = stack.pop();
            count += stack.peek() - top;
        }

        System.out.println(count);
    }
}