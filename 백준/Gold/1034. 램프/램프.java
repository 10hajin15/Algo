import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        K = Integer.parseInt(br.readLine());

        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int zeroCount = 0;
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]);
                if (arr[i][j] == 0) zeroCount++;
            }

            if (zeroCount <= K && (K - zeroCount) % 2 == 0) {
                String key = sb.toString();
                map.put(key, map.getOrDefault(key, 0) + 1);
                answer = Math.max(answer, map.get(key));
            }
        }

        System.out.println(answer);
    }
}