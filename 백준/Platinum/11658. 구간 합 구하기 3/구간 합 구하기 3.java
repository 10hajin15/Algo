import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] tree;

    static int prefixSum(int idx) {
        int result = 0;
        while (idx>0) {
            result += tree[idx];
            idx -= (idx&-idx);
        }
        return result;
    }

    static void update(int idx, int dif) {
        while (idx<=(N*N)) {
            tree[idx] += dif;
            idx += (idx&-idx);
        }
    }

    static int intervalSum(int start, int end) {
        return prefixSum(end) - prefixSum(start-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        tree = new int[(N*N)+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                arr[i][j] = x;
                update((i*N)+j+1, x);
            }
        }

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(st.nextToken());
            if(what == 0) {
                int x = Integer.parseInt(st.nextToken())-1;
                int y = Integer.parseInt(st.nextToken())-1;
                int val = Integer.parseInt(st.nextToken());
                update((x*N)+y+1, val-arr[x][y]);
                arr[x][y] = val;
            } else {
                int x1 = Integer.parseInt(st.nextToken())-1;
                int y1 = Integer.parseInt(st.nextToken())-1;
                int x2 = Integer.parseInt(st.nextToken())-1;
                int y2 = Integer.parseInt(st.nextToken())-1;
                int sum = 0;
                for(int i=0; i<(x2-x1)+1; i++) {
                    int left = (x1+i)*N+y1+1;
                    int right = (x1+i)*N+y2+1;
                    int s = intervalSum(left, right);
                    sum += s;
                }
                System.out.println(sum);
            }
        }
    }
}