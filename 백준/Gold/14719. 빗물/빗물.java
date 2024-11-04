import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] arr = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            int num = Integer.parseInt(st.nextToken());
            for(int j=0; j<num; j++) {
                arr[H-j-1][i] = 1;
            }
        }

        for(int i=0; i<H; i++) {
            int j = 0;
            while(true) {
                if(j>=W) break;
                if(arr[i][j] == 1) break;
                arr[i][j] = 1;
                j++;
            }
        }

        for(int i=0; i<H; i++) {
            int j = W-1;
            while(true) {
                if(j<0) break;
                if(arr[i][j] == 1) break;
                arr[i][j] = 1;
                j--;
            }
        }

        int answer = 0;
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                if(arr[i][j] == 0) answer++;
            }
        }

        System.out.println(answer);
    }
}