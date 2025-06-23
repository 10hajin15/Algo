import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] answer = new int[N];
        int[] freq = new int[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            freq[arr[i]]++;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && freq[arr[i]] >= freq[arr[stack.peek()]]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = arr[stack.peek()];
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString());
    }
}