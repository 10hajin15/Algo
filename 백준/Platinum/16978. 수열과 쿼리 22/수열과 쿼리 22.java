import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] arr;
    static long[][] tree;
    static long[] versionRoots;
    static int nextNode;

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node][2] = arr[start];
        } else {
            int mid = (start + end) / 2;
            long left = nextNode++;
            long right = nextNode++;
            tree[node][0] = left;
            tree[node][1] = right;
            init((int) left, start, mid);
            init((int) right, mid + 1, end);
            tree[node][2] = tree[(int) left][2] + tree[(int) right][2];
        }
    }

    static long update(long prevNode, int start, int end, int idx, long value) {
        long newNode = nextNode++;

        tree[(int) newNode][0] = tree[(int) prevNode][0];
        tree[(int) newNode][1] = tree[(int) prevNode][1];

        if (start == end) {
            tree[(int) newNode][2] = value;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                long left = update(tree[(int) prevNode][0], start, mid, idx, value);
                tree[(int) newNode][0] = left;
            } else {
                long right = update(tree[(int) prevNode][1], mid + 1, end, idx, value);
                tree[(int) newNode][1] = right;
            }
            tree[(int) newNode][2] = tree[(int) tree[(int) newNode][0]][2] + tree[(int) tree[(int) newNode][1]][2];
        }
        return newNode;
    }

    static long query(long node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[(int) node][2];
        }
        int mid = (start + end) / 2;
        long lSum = query(tree[(int) node][0], start, mid, left, right);
        long rSum = query(tree[(int) node][1], mid + 1, end, left, right);
        return lSum + rSum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        int h = 30 * N + 4 * M;
        tree = new long[h][3];
        versionRoots = new long[M + 1];
        nextNode = 1;

        versionRoots[0] = nextNode++;
        init((int) versionRoots[0], 0, N - 1);

        StringBuilder sb = new StringBuilder();
        int curVer = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long value = Long.parseLong(st.nextToken());
                curVer++;
                versionRoots[curVer] = update(versionRoots[curVer - 1], 0, N - 1, idx, value);
            } else if (type == 2) {
                int k = Integer.parseInt(st.nextToken());
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                sb.append(query(versionRoots[k], 0, N - 1, left, right)).append("\n");
            }
        }

        System.out.print(sb);
    }
}