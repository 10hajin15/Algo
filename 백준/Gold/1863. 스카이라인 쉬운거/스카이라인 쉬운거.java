import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && stack.peek() > b) {
                answer++;
                stack.pop();
            }

            if(!stack.isEmpty() && stack.peek() == b) {
                continue;
            }

            stack.push(b);
        }

        while(!stack.isEmpty()) {
            if(stack.peek() > 0) answer++;
            stack.pop();
        }

        System.out.println(answer);
    }
}