import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int ans;
    static int[][] scores;

    static void permu(int n, int[] tlst, boolean[] visited) {
        if(n == 8) {
            getScore(tlst);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            tlst[n] = i;
            permu(n + 1, tlst, visited);
            visited[i] = false;
        }
    }

    static void getScore(int[] tlst) {
        int[] lst = new int[9];
        for (int i = 0; i < 3; i++) {
            lst[i] = tlst[i];
        }

        for (int i = 4; i < 9; i++) {
            lst[i] = tlst[i-1];
        }

        int sum = 0;
        int idx = 0;

        for(int i=0; i<N; i++) {
            int out = 0;
            boolean[] base = new boolean[3];
            while(true) {
                if(out >= 3) break;

                int member = lst[idx];

                int s = scores[i][member];

                idx = (idx+1) % 9;

                if(s == 0) {
                    out++;
                    continue;
                }

                if(s == 4) {
                    sum++;
                    for (int j = 0; j < 3; j++) {
                        if(base[j]) sum++;
                        base[j] = false;
                    }
                } else {
                    for (int j = 2; j >= 0; j--) {
                        if(!base[j]) continue;

                        if(j+s >= 3) {
                            sum++;
                        } else {
                            base[j+s] = true;
                        }
                        base[j] = false;
                    }

                    base[s-1] = true;
                }
            }
        }

        ans = Math.max(ans, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        scores = new int[N][9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        permu(0, new int[8], new boolean[9]);


        System.out.println(ans);
    }
}