import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] answer;
    static Map<Integer, Integer> counts;
    static TreeSet<Integer> indexSet = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        answer = new int[N];
        counts = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return b[1] - a[1];
        });

        for (int i = 0; i < N; i++) {
            pq.add(new int[]{i, counts.get(arr[i])});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curIdx = cur[0];
            int curCnt = cur[1];

            List<Integer> sameGroup = new ArrayList<>();
            sameGroup.add(curIdx);
            while (!pq.isEmpty() && pq.peek()[1] == curCnt) {
                sameGroup.add(pq.poll()[0]);
            }

            for (int idx : sameGroup) {
                Integer nextIdx = indexSet.higher(idx);
                if (nextIdx == null) {
                    answer[idx] = -1;
                } else {
                    answer[idx] = arr[nextIdx];
                }
            }

            indexSet.addAll(sameGroup);
        }

        StringBuilder sb = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}