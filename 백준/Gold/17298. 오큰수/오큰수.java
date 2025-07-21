import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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

        int[] ans = new int[N];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if(stack.isEmpty() || arr[stack.peek()] > arr[i]) {
                stack.add(i);
                continue;
            }

            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int n = stack.pop();
                ans[n] = arr[i];
            }

            stack.add(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}