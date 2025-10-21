import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int h = (int) Math.ceil(9 * M / 10.0);
        String answer = "NO";

        if (h > N) {
            System.out.println(answer);
            return;
        }

        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < M; i++) {
            counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
            if (counts.get(arr[i]) >= h) {
                System.out.println("YES");
                return;
            }
        }

        for (int i = 1; i <= N - M; i++) {
            int remove = arr[i - 1];
            int add = arr[i + M - 1];

            counts.put(remove, counts.get(remove) - 1);
            if (counts.get(remove) == 0) counts.remove(remove);

            counts.put(add, counts.getOrDefault(add, 0) + 1);

            if (counts.get(add) >= h) {
                answer = "YES";
                break;
            }
        }

        System.out.println(answer);
    }
}