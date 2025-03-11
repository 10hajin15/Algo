import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if (N == 0) break;

            int[] heights = new int[N];
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(getMaxArea(heights, N)).append("\n");
        }
        System.out.print(sb);
    }

    public static long getMaxArea(int[] heights, int N) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i <= N; i++) {
            int h = (i == N) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, (long) height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
