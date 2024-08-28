import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static void init(long[] tmp, long[] tree, int node, int start, int end) {
        if(start == end) {
            tree[node] = tmp[start];
        } else {
            init(tmp, tree, node*2, start, (start+end)/2);
            init(tmp, tree, node*2+1, (start+end)/2+1, end);
            tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        }
    }

    static void update(long[] tmp, long[] tree, int node, int start, int end, int idx, long val) {
        if(idx < start || idx > end) {
            return;
        }
        if(start == end) {
            tmp[idx] = val;
            tree[node] = val;
            return;
        }
        update(tmp, tree, node*2, start, (start+end)/2, idx, val);
        update(tmp, tree, node*2+1, (start+end)/2+1, end, idx, val);
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }

    static long query(long[] tree, int node, int start, int end, int left, int right) {
        if(left  > end || right < start) {
            return -1;
        }
        if(left <= start && end <= right) {
            return tree[node];
        }
        long lmin = query(tree, node * 2, start, (start + end) / 2, left, right);
        long rmin = query(tree, node * 2+1, (start+end)/2+1, end, left, right);

        if(lmin == -1) {
            return rmin;
        } else if(rmin == -1) {
            return lmin;
        } else {
            return Math.min(lmin, rmin);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] tmp = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tmp[i] = Long.parseLong(st.nextToken());
        }

        int k = 0;
        while ((1 << k) <= N) {
            k++;
        }
        long[] tree = new long[(1 << (k+1))];
        init(tmp, tree, 1, 0, N - 1);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(st.nextToken());
            if (what == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                update(tmp, tree, 1, 0, N - 1, idx - 1, val);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(tree, 1, 0, N-1, left-1, right-1)).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}