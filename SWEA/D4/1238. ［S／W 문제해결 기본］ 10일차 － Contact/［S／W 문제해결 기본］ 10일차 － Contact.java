import java.io.*;
import java.util.*;

public class Solution {
    static Map<Integer, ArrayList<Integer>> arr;
    static int ans = 0;
    static int N, start;

    static void bfs() {
        boolean[] visited = new boolean[101];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start, 0});
        visited[start] = true;
        int cnt = -1;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int num = now[0];
            int n = now[1];

            if(n > cnt) {
                cnt = n;
                ans = num;
            } else if(n==cnt) {
                ans = Math.max(num, ans);
            }


            if (arr.get(num) != null) {
                for(int next: arr.get(num)) {
                    if(!visited[next]) {
                        visited[next] = true;
                        q.offer(new int[] {next, n+1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int tc=1; tc<=10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            arr = new HashMap<>();
            ans = 0;

            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                if(!arr.containsKey(from)) {
                    arr.put(from, new ArrayList<>());
                }
                arr.get(from).add(to);
            }

            bfs();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}