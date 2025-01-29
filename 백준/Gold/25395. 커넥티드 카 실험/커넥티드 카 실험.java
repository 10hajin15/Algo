import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static long[] x, h;
    static boolean[] visited;
    static List<Car> cars;
    static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1;

        x = new long[N];
        h = new long[N];
        visited = new boolean[N];
        cars = new ArrayList<>();
        index = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            x[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            h[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            cars.add(new Car(i, x[i], h[i]));
        }
        Collections.sort(cars);

        for (int i = 0; i < N; i++) {
            index[cars.get(i).num] = i;
        }

        bfs(S);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (visited[i]) sb.append(i + 1).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Car> q = new LinkedList<>();
        q.add(cars.get(index[start]));
        visited[start] = true;

        int left = index[start], right = index[start];

        while (true) {
            if (q.isEmpty()) break;
            Car now = q.poll();

            while (true) {
                if (left == 0 || visited[cars.get(left - 1).num]) break;
                if (cars.get(left - 1).x < now.x - now.h) break;
                left--;
                q.add(cars.get(left));
                visited[cars.get(left).num] = true;
            }

            while (true) {
                if (right == N - 1 || visited[cars.get(right + 1).num]) break;
                if (cars.get(right + 1).x > now.x + now.h) break;
                right++;
                q.add(cars.get(right));
                visited[cars.get(right).num] = true;
            }
        }
    }

    static class Car implements Comparable<Car> {
        int num;
        long x, h;

        public Car(int num, long x, long h) {
            this.num = num;
            this.x = x;
            this.h = h;
        }

        public int compareTo(Car c) {
            return Long.compare(this.x, c.x);
        }
    }
}