import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> mapA = getMap(A);

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> mapB = getMap(B);

        long cnt = 0;
        for (int key : mapB.keySet()) {
            long num = T - key;
            if (mapA.containsKey((int)num)) {
                cnt += (long) mapA.get((int)num) * mapB.get(key);
            }
        }

        System.out.println(cnt);
    }

    static Map<Integer, Integer> getMap(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                m.put(sum, m.getOrDefault(sum, 0) + 1);
            }
        }

        return m;
    }
}
