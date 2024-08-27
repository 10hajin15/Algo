import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static void init(long[] tmp, long[] tree, int node, int start, int end) {
        if (start == end) tree[node] = tmp[start];
        else {
            init(tmp, tree, node*2, start, (start+end)/2);
            init(tmp, tree, node*2+1, (start+end)/2+1, end);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    static void update(long[] tmp, long[] tree, int node, int start, int end, int idx, long val) {
        if (idx < start || idx > end) return;

        if(start == end) {
            tmp[idx] = val;
            tree[node] = val;
            return;
        }
        update(tmp, tree, node*2, start, (start+end)/2, idx, val);
        update(tmp, tree, node*2+1, (start+end)/2+1, end, idx, val);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    static long query(long[] tree, int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && end <= right) {
            return tree[node];
        }

        long lsum = query(tree, node*2, start, (start+end)/2, left, right);
        long rsum = query(tree, node*2+1, (start+end)/2+1, end, left, right);

        return lsum+rsum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        M += K;

        long[] tmp = new long[N];
        for(int i=0; i<N; i++) {
            tmp[i] = Long.parseLong(br.readLine());
        }

        int k= 0;
        while((1<<k) <= N) {
            k++;
        }
        long[] tree = new long[(1<<k)*2];
        init(tmp, tree, 1,0, N-1);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(st.nextToken());
            if(what==1) {
                int index = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                update(tmp, tree, 1, 0, N-1, index-1, val);
            } else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                bw.write(query(tree, 1, 0, N-1, left-1, right-1)+"\n");
            }
        }
        bw.flush();
    }
}