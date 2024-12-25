import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static int[] tree;

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
        } else {
            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            tree[node] = compare(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    static int compare(int x, int y) {
        if (arr[x] < arr[y]) return x;
        else if (arr[x] > arr[y]) return y;
        else return Math.min(x, y);
    }

    static void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            arr[idx] = value;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) update(node * 2, start, mid, idx, value);
            else update(node * 2 + 1, mid + 1, end, idx, value);
            tree[node] = compare(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return -1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        int lQuery = query(node * 2, start, mid, left, right);
        int rQuery = query(node * 2 + 1, mid + 1, end, left, right);

        if (lQuery == -1) return rQuery;
        if (rQuery == -1) return lQuery;

        return compare(lQuery, rQuery);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int k = 0;
        while((1<<k) <= N) k++;
        tree = new int[(1 << (k + 1))];

        init(1, 0, N - 1);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                update(1, 0, N - 1, idx, value);
            } else if (type == 2) {
                int resultIdx = query(1, 0, N - 1, 0, N - 1);
                sb.append(resultIdx + 1).append("\n");
            }
        }
        System.out.print(sb);
    }
}