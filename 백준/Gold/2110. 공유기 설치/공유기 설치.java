import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 1;
        int right = arr[N-1];


        while(true) {
            if(left > right) break;
            int mid = (left+right) / 2;

            int cnt=1;
            int pos=0;
            for(int i=1; i<N; i++) {
                if(arr[i]-arr[pos] >= mid) {
                    pos = i;
                    cnt++;
                }
            }

            if(cnt < C) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.println(left-1);
    }
}