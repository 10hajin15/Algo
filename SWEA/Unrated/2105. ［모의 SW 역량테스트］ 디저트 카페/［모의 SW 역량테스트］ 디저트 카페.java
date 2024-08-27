import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int ans = 0;
    static int[][] arr;
    static int N;

    static void cal(int i, int j, int left, int right) {
        Set<Integer> check = new HashSet<>();
        int n =1;
        check.add(arr[i][j]);
        int li =0, lj=0, ri=0, rj=0;

        for(int x=1; x<=left; x++) {
            check.add(arr[i+x][j-x]);
            li = i+x; lj = j-x;
            n+=1;
        }
        if(n != check.size()) return;

        for(int x=1; x<=right; x++) {
            check.add(arr[i+x][j+x]);
            ri = i+x; rj = j+x;
            n+=1;
        }
        if(n != check.size()) return;

        for(int x=1; x<=right; x++) {
            if(li+x>=N || lj+x>=N) return;
            check.add(arr[li+x][lj+x]);
            n+=1;
        }
        if(n != check.size()) return;

        for(int x=1; x<left; x++) {
            if(ri+x>=N || rj-x<0) return;
            check.add(arr[ri+x][rj-x]);
            n+=1;
        }
        if(n != check.size()) return;

        ans = Math.max(ans, check.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            ans = 0;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N-1; i++) {
                for(int j=0; j<N-1; j++) {

                    for(int left=1; left<N; left++) {
                        int li = i+left;
                        int lj = j-left;
                        if(li>=N || lj<0) break;
                        for(int right=1; right<N; right++) {
                            int ri=i+right;
                            int rj=j+right;
                            if(ri>=N || rj>=N) break;
                            cal(i, j, left, right);
                        }
                    }
                }
            }

            if(ans==0) ans = -1;
            System.out.println("#"+tc+" "+ans);
        }
    }
}