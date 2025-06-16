import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Customer> customers = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customers.add(new Customer(id, w));
        }

        long[] counterEndTime = new long[K];

        PriorityQueue<Work> pq = new PriorityQueue<>();

        for (int i = 0; i < K && !customers.isEmpty(); i++) {
            Customer c = customers.poll();
            counterEndTime[i] = c.weight;
            pq.add(new Work(counterEndTime[i], i, c.id));
        }

        while (!customers.isEmpty()) {
            int minIdx = 0;
            for (int i = 1; i < K; i++) {
                if (counterEndTime[i] < counterEndTime[minIdx]) {
                    minIdx = i;
                } else if (counterEndTime[i] == counterEndTime[minIdx] && i < minIdx) {
                    minIdx = i;
                }
            }

            Customer c = customers.poll();
            counterEndTime[minIdx] += c.weight;
            pq.add(new Work(counterEndTime[minIdx], minIdx, c.id));
        }

        long result = 0;
        long order = 1;
        while (!pq.isEmpty()) {
            Work w = pq.poll();
            result += order * w.id;
            order++;
        }

        System.out.println(result);
    }

    static class Customer {
        int id;
        int weight;

        Customer(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    static class Work implements Comparable<Work> {
        long endTime;
        int counterNum;
        int id;

        Work(long endTime, int counterNum, int id) {
            this.endTime = endTime;
            this.counterNum = counterNum;
            this.id = id;
        }

        @Override
        public int compareTo(Work o) {
            if (this.endTime == o.endTime) {
                return Integer.compare(o.counterNum, this.counterNum);
            }
            return Long.compare(this.endTime, o.endTime);
        }
    }
}