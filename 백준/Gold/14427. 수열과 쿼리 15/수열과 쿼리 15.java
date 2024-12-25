import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(arr[i], k -> new TreeSet<>()).add(i);
        }

        int m = Integer.parseInt(br.readLine());

        for (int q = 0; q < m; q++) {
            st = new StringTokenizer(br.readLine());
            int queryType = Integer.parseInt(st.nextToken());

            if (queryType == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                int oldVal = arr[i];
                map.get(oldVal).remove(i);
                if (map.get(oldVal).isEmpty()) {
                    map.remove(oldVal);
                }

                arr[i] = v;
                map.computeIfAbsent(v, k -> new TreeSet<>()).add(i);
            } else if (queryType == 2) {
                int minValue = map.firstKey();
                int minIndex = map.get(minValue).first();
                sb.append(minIndex).append("\n");
            }
        }

        System.out.print(sb);
    }
}