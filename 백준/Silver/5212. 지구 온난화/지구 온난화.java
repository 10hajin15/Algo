import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 바다로 주변 감싸기
        String[] map = new String[R+2];
        StringBuilder padding = new StringBuilder();
        for(int i=0; i<C+2; i++) {
            padding.append(".");
        }
        map[0] = padding.toString();
        map[R+1] = padding.toString();

        for(int i=1; i<R+1; i++) {
            padding = new StringBuilder();
            padding.append(".").append(br.readLine()).append(".");
            map[i] = padding.toString();
        }

        char[][] ans = new char[R][C];
        int sx = R+2, sy = C+2;
        int ex = -1, ey = -1;

        // X일 때 사방탐색
        for(int i=1; i<R+1; i++) {
            for(int j=1; j<C+1; j++) {
                if(map[i].charAt(j) == 'X') {
                    int cnt = 0;
                    for(int k=0; k<4; k++) {
                        int ni = i+dirs[k][0];
                        int nj = j+dirs[k][1];

                        if(map[ni].charAt(nj) == '.') cnt++;
                    }
                    if(cnt >= 3) {
                        ans[i-1][j-1] = '.';
                    } else {
                        if(sx>i) sx = i;
                        if(sy>j) sy = j;
                        if(ex<i) ex = i;
                        if(ey<j) ey = j;
                        ans[i-1][j-1] = 'X';
                    }
                } else {
                    ans[i-1][j-1] = '.';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=sx-1; i<=ex-1; i++) {
            for(int j=sy-1; j<=ey-1; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}