import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());


        PriorityQueue<int[]> in = new PriorityQueue<>((o1,o2)-> Integer.compare(o1[0],o2[0]));
        PriorityQueue<Integer> out = new PriorityQueue<>();


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            in.offer(new int[] {start, end});
        }

        int cnt = 1;
        int[] arr = new int[N+1];

        while(!in.isEmpty()) {
           int[] now = in.poll();
           int end = now[1];

           if(end < 0) {
               arr[-end] += 1;
               out.offer(-end);
           } else {
               if(!out.isEmpty()) {
                   int num = out.poll();
                   in.offer(new int[] {end, -num});
               } else {
                   in.offer(new int[] {end, -cnt});
                   cnt++;
               }
           }
        }

        System.out.println(cnt-1);
        for(int i=1; i<cnt; i++) {
            System.out.print(arr[i]+" ");
        }
    }

}