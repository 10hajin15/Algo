import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static void init(int[] tmp, int[] tree, int node, int start, int end) {
        if(start == end) {
            tree[node] = tmp[start];
        } else {
            init(tmp, tree, node*2, start, (start+end)/2);
            init(tmp, tree, node*2+1, (start+end)/2+1, end);
            tree[node] = Math.max(tree[node*2],tree[node*2+1]);
        }
    }

    static int query(int[] tree, int node, int start, int end, int left, int right) {
        if(left > end || right < start) return -1;

        if(left <= start && right >= end) {
            return tree[node];
        }

        int lMax = query(tree, node*2, start, (start+end)/2, left, right);
        int rMax = query(tree, node*2+1, (start+end)/2+1, end, left, right);

        if(lMax==-1) {
            return rMax;
        } else if(rMax==-1) {
            return lMax;
        } else {
            return Math.max(lMax, rMax);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] tmp = new int[N];
        int i = 0;
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            tmp[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        int k = 0;
        while((1<<k) <= N) k++;
        int[] tree = new int[(1<<(k+1))];

        init(tmp, tree, 1, 0, N-1);

        for (int left = M-1; left < N-M+1; left++) {
            sb.append(query(tree, 1, 0, N-1, left-M+1, left+M-1)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}