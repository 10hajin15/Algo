import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<int[][]> combi = new ArrayList<>();
    static ArrayList<int[]> chickens = new ArrayList<>();
    static ArrayList<int[]> houses = new ArrayList<>();
    static int ans, cnt;

    static void combination(int n, int start, int[] tmp) {
        if(n==M) {
            int[][] t = new int[M][2];
            for(int i=0;i<M;i++) {
                t[i][0] = chickens.get(tmp[i])[0];
                t[i][1] = chickens.get(tmp[i])[1];
            }
            combi.add(t);
            return;
        }

        for (int i = start; i < cnt; i++) {
            tmp[n] = i;
            combination(n+1, i+1, tmp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num==1) {
                    houses.add(new int[]{i,j});
                } else if(num==2) {
                    chickens.add(new int[]{i,j});
                    cnt++;
                }
            }
        }

        combination(0, 0, new int[M]);

        ans = Integer.MAX_VALUE;
        for(int[][] chicken : combi) {
            int total_distance = 0;
            for(int[] house: houses) {
                int distance = Integer.MAX_VALUE;
                for(int[] chi: chicken) {
                    distance = Math.min(distance, Math.abs(house[0] - chi[0]) + Math.abs(house[1] - chi[1]));
                }
                total_distance += distance;
            }
            ans = Math.min(ans, total_distance);
        }

        System.out.println(ans);
    }
}