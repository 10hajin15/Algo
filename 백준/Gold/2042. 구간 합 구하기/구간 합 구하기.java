import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int k = 0;
        while ((1 << k) <= N) {
            k++;
        }
        long[] tree = new long[(1 << k) * 2];  // 배열을 long 타입으로 변경

        // 트리 초기화
        for (int i = (1 << k); i < (1 << k) + N; i++) {
            tree[i] = Long.parseLong(br.readLine().trim());  // 입력값을 long 타입으로 처리
        }

        // 내부 노드 채우기
        for (int i = (1 << k) - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        // 질의 및 업데이트 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());  // c를 long 타입으로 처리

            if (a == 1) {
                int idx = (1 << k) + b - 1;
                long value = c - tree[idx];
                tree[idx] = c;

                while (idx > 1) {
                    idx /= 2;
                    tree[idx] += value;
                }
            } else {
                long sum = 0;
                int si = (1 << k) + b - 1;
                int ei = (1 << k) + (int) c - 1;

                while (si <= ei) {
                    if (si % 2 == 1) sum += tree[si++];
                    if (ei % 2 == 0) sum += tree[ei--];
                    si /= 2;
                    ei /= 2;
                }

                System.out.println(sum);
            }
        }
    }
}