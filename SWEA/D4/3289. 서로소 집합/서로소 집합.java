import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int[] parents;

    static int findSet(int a) {
        if (a != parents[a]) {
            parents[a] = findSet(parents[a]); // Path compression
        }
        return parents[a];
    }

    static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parents = new int[N];
            for(int i=0; i<N; i++) {
                parents[i] = i;
            }

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int what = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;

                if(what==1) {
                    sb.append(findSet(a) != findSet(b) ? "0" : "1");
                } else {
                    union(a, b);
                }
            }
            sb.append("\n");
            bw.write(sb.toString());
            bw.flush();
        }
        br.close();
        bw.close();
    }
}