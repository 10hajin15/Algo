import java.io.*;
import java.util.*;

public class Solution {
    static int ans;
    static int N, M, C;
    static List<int[]> lst;
    static int aSum;
    static int bSum;
    static int[] arr;

    static void subSet(int n, boolean[] checked, int[] tlst, char type) {
        if(n == M) {
            int sum = 0;
            int doubleSum = 0;
            for(int i = 0; i < M; i++) {
                if(checked[i]) {
                    sum += tlst[i];
                    doubleSum += (tlst[i]*tlst[i]);
                }
                if(sum>C) return;
            }
            if(type=='a') aSum = Math.max(aSum, doubleSum);
            else if(type=='b') bSum = Math.max(bSum, doubleSum);
            return;
        }
        checked[n] = true;
        subSet(n+1, checked, tlst, type);
        checked[n] = false;
        subSet(n+1, checked, tlst, type);
    }

    static int cal(int[] a, int[] b) {
        aSum = Integer.MIN_VALUE;
        bSum = Integer.MIN_VALUE;

        subSet(0, new boolean[M], a, 'a');
        subSet(0, new boolean[M], b, 'b');

        return aSum+bSum;
    }

    static boolean isDupl(int[] a, int[] b) {
        boolean[] visited = new boolean[N*N];

        for(int i=0; i<a.length;i++) {
            visited[a[i]] = true;
        }

        for(int i=0; i<b.length;i++) {
            if(visited[b[i]]) return true;
            visited[b[i]] = true;
        }

        return false;
    }

    static void combi(int n, int start, int[] tmp) {
        if(n>=2) {
            int[] a = lst.get(tmp[0]);
            int[] b = lst.get(tmp[1]);

            if(!isDupl(a, b)) {
                int[] alst = new int[M];
                int[] blst = new int[M];
                for(int i=0; i<M; i++) {
                    alst[i] = arr[a[i]];
                    blst[i] = arr[b[i]];
                }

                int num = cal(alst, blst);

                ans = Math.max(ans, num);
            }
            return;
        }

        for (int i = start; i < lst.size(); i++) {
            tmp[n] = i;
            combi(n+1, i, tmp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            arr = new int[N*N];
            ans = Integer.MIN_VALUE;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j= 0; j<N; j++) {
                    arr[i*N+j] = Integer.parseInt(st.nextToken());
                }
            }

            lst = new ArrayList<>();
            for(int i=0; i<N*N; i++) {
                int[] tmp = new int[M];
                int idx = 0;
                int col = i%N;
                if(col+M-1>=N) continue;
                for(int j=0; j<M; j++) {
                    tmp[idx++] = i+j;
                }
                lst.add(tmp);
            }

            int[] tmp = new int[2];
            combi(0, 0, tmp);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}