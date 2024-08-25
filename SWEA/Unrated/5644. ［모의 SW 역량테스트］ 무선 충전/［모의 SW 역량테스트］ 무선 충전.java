import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    static int M, A;
    static int[] moveA;
    static int[] moveB;
    static BC[] arr;

    static int[] R = {0, -1, 0, 1, 0};
    static int[] C = {0, 0, 1, 0, -1};

    static int ax, ay, bx, by;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            ans = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M];
            moveB = new int[M];

            ax = 0; ay = 0; bx = 9; by = 9;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            arr = new BC[A];

            for(int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                int cover = Integer.parseInt(st.nextToken());
                int perform = Integer.parseInt(st.nextToken());

                arr[i] = new BC(r, c, cover, perform);
            }

            charge();

            for(int i = 0; i < M; i++) {
                ax += R[moveA[i]];
                ay += C[moveA[i]];
                bx += R[moveB[i]];
                by += C[moveB[i]];
                charge();
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    static void charge() {
        int maxCharge = 0;

        for (int i = 0; i < A; i++) {
            int chargeA = 0;

            if (Math.abs(ax - arr[i].x) + Math.abs(ay - arr[i].y) <= arr[i].c) {
                chargeA = arr[i].p;
            }

            for (int j = 0; j < A; j++) {
                int chargeB = 0;

                if (Math.abs(bx - arr[j].x) + Math.abs(by - arr[j].y) <= arr[j].c) {
                    chargeB = arr[j].p;
                }

                int totalCharge;
                if (i == j && chargeA > 0) {
                    totalCharge = chargeA;
                } else {
                    totalCharge = chargeA + chargeB;
                }

                maxCharge = Math.max(maxCharge, totalCharge);
            }
        }

        ans += maxCharge;
    }

    static class BC {
        int x, y, c, p;
        BC(int x, int y, int c, int p){
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

}