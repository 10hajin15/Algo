import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N;
    static int[][] arr;
    static ArrayList<int[]> zero;
    static boolean flag;
    static Set<Integer>[] sec;
    static Set<Integer>[] row;
    static Set<Integer>[] col;

    static void dfs(int n) {
        if(flag) return;

        if(n == N) {
            flag = true;
            return;
        }

        int[] point = zero.get(n);
        int x = point[0];
        int y = point[1];
        boolean[] cant = new boolean[10];

        int sNum = findSecNum(x, y);

        for (int num : row[x]) cant[num] = true;
        for (int num : col[y]) cant[num] = true;
        for (int num : sec[sNum]) cant[num] = true;

        for (int i = 1; i < 10; i++) {
            if(cant[i]) continue;
            arr[x][y] = i;
            row[x].add(i);
            col[y].add(i);
            sec[sNum].add(i);
            dfs(n+1);

            if(flag) return;

            arr[x][y] = 0;
            row[x].remove(i);
            col[y].remove(i);
            sec[sNum].remove(i);
        }
    }

    static int findSecNum(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9][9];
        zero = new ArrayList<>();
        flag = false;
        sec = new HashSet[9];
        row = new HashSet[9];
        col = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            sec[i] = new HashSet<>();
            row[i] = new HashSet<>();
            col[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            String num = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = num.charAt(j) - '0';
                if(arr[i][j] == 0) {
                    zero.add(new int[]{i, j});
                } else {
                    int sNum = findSecNum(i, j);
                    row[i].add(arr[i][j]);
                    col[j].add(arr[i][j]);
                    sec[sNum].add(arr[i][j]);
                }
            }
        }

        N = zero.size();
        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}