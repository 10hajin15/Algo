import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static long[] arr;
    static int n, m, k;

    static long prefixSum(int i) {
        long result = 0;
        while (i>0) {
            result += tree[i];
            i -= (i&-i);
        }
        return result;
    }

    static void update(int i, long dif) {
        while (i<=n) {
            tree[i] += dif;
            i += (i&-i);
        }
    }

    static long intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        tree = new long[n+1];
        arr = new long[n+1];

        for(int i=1; i<=n; i++) {
            long x = Long.parseLong(br.readLine());
            arr[i] = x;
            update(i, x);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a==1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                update(b, c-arr[b]);
                arr[b] = c;
            } else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(intervalSum(b, c)).append("\n");
            }
        }
        System.out.print(sb);
    }
}