import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(m, v));
        }

        Collections.sort(jewels);

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long answer = 0;
        int idx = 0;
        for (int i = 0; i < K; i++) {
            int bagWeight = bags[i];

            while(idx < N && jewels.get(idx).m <= bagWeight) {
                pq.add(jewels.get(idx).v);
                idx++;
            }

            if(!pq.isEmpty()) answer += pq.poll();
        }

        System.out.println(answer);
    }

    static class Jewel implements Comparable<Jewel> {
        int m, v;

        Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel j) {
            return Integer.compare(m, j.m);
        }
    }
}