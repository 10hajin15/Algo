import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] lst = new int[N+1];
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lst[b] += 1;
            graph[a].add(b);
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++) {
            if(lst[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int tmp: graph[now]) {
                lst[tmp] -= 1;
                if(lst[tmp] == 0) {
                    q.add(tmp);
                }
            }
        }

        System.out.println(sb.toString());
    }
}