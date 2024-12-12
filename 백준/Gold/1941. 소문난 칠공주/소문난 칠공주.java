import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] arr;
    static int ans;
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static void dfs(int n, int start, ArrayList<Integer> lst) {
        if(n == 7) {
            if(isGroup(lst)) ans++;
            return;
        }

        for (int i = start; i < 25; i++) {
            lst.add(n, i);
            dfs(n+1, i+1, lst);
            lst.remove(n);
        }
    }

    static boolean isGroup(ArrayList<Integer> lst) {
        int sCnt = 0;
        for(int num: lst) {
            int x = num/5;
            int y = num%5;
            if(arr[x][y] == 'S') sCnt++;
        }
        if(sCnt < 4) return false;

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[25];
        visited[lst.get(0)] = true;
        q.add(lst.get(0));
        int cnt = 1;

        while(!q.isEmpty()) {
            int now = q.poll();

            int di = now/5;
            int dj = now%5;

            for (int[] dir: dirs) {
                int ni = di+dir[0];
                int nj = dj+dir[1];

                if(ni<0 || ni>=5 || nj<0 || nj>=5) continue;
                int n = ni*5 + nj;
                if(!lst.contains(n) || visited[n]) continue;

                visited[n] = true;
                q.add(n);
                cnt++;
            }
        }

        return cnt == 7;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            arr[i] = str.toCharArray();
        }

        dfs(0, 0, new ArrayList<Integer>());

        System.out.println(ans);
    }
}