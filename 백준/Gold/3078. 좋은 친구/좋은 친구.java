import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer>[] lenCount = new LinkedList[21];
        for (int i = 2; i <= 20; i++) {
            lenCount[i] = new LinkedList<>();
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            int len = name.length();

            while (!lenCount[len].isEmpty() && lenCount[len].peek() < i - K) {
                lenCount[len].poll();
            }

            ans += lenCount[len].size();

            lenCount[len].add(i);
        }

        System.out.println(ans);
    }
}