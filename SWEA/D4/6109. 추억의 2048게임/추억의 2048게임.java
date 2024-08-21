import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int[][] lst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            lst = new int[N][N];
            int[][] ans = new int[N][N];
            String type = st.nextToken();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    lst[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(type.equals("up")) {
                for(int j=0; j<N; j++) {
                    List<Integer> tmp = new ArrayList<>();
                    int prev = lst[0][j];
                    for(int i=1; i<N; i++) {
                        if(lst[i][j] == 0) continue;
                        if(prev == lst[i][j]) {
                            tmp.add(prev*2);
                            prev = 0;
                        } else {
                            if(prev!=0) tmp.add(prev);
                            prev = lst[i][j];
                        }
                    }
                    if(prev != 0) tmp.add(prev);
                    for(int x=0; x<tmp.size(); x++) {
                        ans[x][j] = tmp.get(x);
                    }
                }
            } else if(type.equals("down")) {
                for(int j=0; j<N; j++) {
                    List<Integer> tmp = new ArrayList<>();
                    int prev = lst[N-1][j];
                    for(int i=N-2; i>=0; i--) {
                        if(lst[i][j] == 0) continue;
                        if(prev == lst[i][j]) {
                            tmp.add(prev*2);
                            prev = 0;
                        } else {
                            if(prev!=0) tmp.add(prev);
                            prev = lst[i][j];
                        }
                    }
                    if(prev != 0) tmp.add(prev);
                    for(int x=0; x<tmp.size(); x++) {
                        ans[N-1-x][j] = tmp.get(x);
                    }
                }
            } else if(type.equals("right")) {
                for(int i=0; i<N; i++) {
                    List<Integer> tmp = new ArrayList<>();
                    int prev = lst[i][N-1];
                    for(int j=N-2; j>=0; j--) {
                        if(lst[i][j] == 0) continue;
                        if(prev == lst[i][j]) {
                            tmp.add(prev*2);
                            prev = 0;
                        } else {
                            if(prev!=0) tmp.add(prev);
                            prev = lst[i][j];
                        }
                    }
                    if(prev != 0) tmp.add(prev);
                    for(int x=0; x<tmp.size(); x++) {
                        ans[i][N-1-x] = tmp.get(x);
                    }
                }
            } else {
                for(int i=0; i<N; i++) {
                    List<Integer> tmp = new ArrayList<>();
                    int prev = lst[i][0];
                    for(int j=1; j<N; j++) {
                        if(lst[i][j] == 0) continue;
                        if(prev == lst[i][j]) {
                            tmp.add(prev*2);
                            prev = 0;
                        } else {
                            if(prev!=0) tmp.add(prev);
                            prev = lst[i][j];
                        }
                    }
                    if(prev != 0) tmp.add(prev);
                    for(int x=0; x<tmp.size(); x++) {
                        ans[i][x] = tmp.get(x);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append("\n");
            for(int i=0; i<N; i++) {
                for(int j=0; j<N;j++) {
                    sb.append(ans[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}