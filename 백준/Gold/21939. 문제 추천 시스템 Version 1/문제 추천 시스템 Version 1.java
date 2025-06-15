import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> levels = new HashMap<>();

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            levels.put(P, L);
            maxHeap.add(new int[]{L, P});
            minHeap.add(new int[]{L, P});
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                levels.put(P, L);
                maxHeap.add(new int[]{L, P});
                minHeap.add(new int[]{L, P});
            } else if (cmd.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                levels.remove(P);
            } else {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    while (!maxHeap.isEmpty()) {
                        int[] top = maxHeap.peek();
                        if (levels.containsKey(top[1]) && levels.get(top[1]) == top[0]) {
                            sb.append(top[1]).append("\n");
                            break;
                        }
                        maxHeap.poll();
                    }
                } else {
                    while (!minHeap.isEmpty()) {
                        int[] top = minHeap.peek();
                        if (levels.containsKey(top[1]) && levels.get(top[1]) == top[0]) {
                            sb.append(top[1]).append("\n");
                            break;
                        }
                        minHeap.poll();
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }
}