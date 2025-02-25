import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] tree, lazy;

    static void update(int node, int start, int end, int left, int right) {
        propagate(node, start, end);

        if (left > end || right < start) return;
        if (left <= start && end <= right) {
            lazy[node] ^= 1;
            propagate(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void propagate(int node, int start, int end) {
        if (lazy[node] == 1) {
            tree[node] = (end - start + 1) - tree[node];
            if (start != end) {
                lazy[node * 2] ^= 1;
                lazy[node * 2 + 1] ^= 1;
            }
            lazy[node] = 0;
        }
    }

    static int query(int node, int start, int end, int left, int right) {
        propagate(node, start, end);

        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int lsum = query(node * 2, start, mid, left, right);
        int rsum = query(node * 2 + 1, mid + 1, end, left, right);
        return lsum + rsum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[4 * N];
        lazy = new int[4 * N];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (type == 0) {
                update(1, 0, N - 1, a, b);
            } else {
                sb.append(query(1, 0, N - 1, a, b)).append("\n");
            }
        }

        System.out.print(sb);
    }
}
