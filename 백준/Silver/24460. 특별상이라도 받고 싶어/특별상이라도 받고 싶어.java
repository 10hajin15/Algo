import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] seats;
    static final int DIV = 4;

    static int dfs(int x, int y, int size) {
        if(size < 2) return seats[x][y];

        int mid = size/2;
        int[] tmp = new int[DIV];

        for(int i=0; i<DIV; i++) {
            int nx = x + (i%2)*mid;
            int ny = y + (i/2)*mid;
            tmp[i] = dfs(nx, ny, mid);
        }
        Arrays.sort(tmp);
        return tmp[1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        seats = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                seats[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0,0, N));
    }
}