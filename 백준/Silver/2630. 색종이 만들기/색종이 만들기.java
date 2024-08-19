import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int white, blue;
    static int[][] arr;

    static boolean check(int x, int y, int size) {
        int paper = arr[x][y];

        for(int i=0; i<size; i++) {
            for(int j=0;j<size; j++) {
                if(paper != arr[x+i][y+j]) return false;
            }
        }

        return true;
    }

    static void dfs(int x, int y, int size) {
        if(check(x,y,size)) {
            if(arr[x][y] == 0) white++;
            else blue++;
            return;
        }

        int mid = size/2;

        dfs(x, y,mid);
        dfs(x+mid, y, mid);
        dfs(x,y+mid, mid);
        dfs(x+mid,y+mid,mid);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }
}