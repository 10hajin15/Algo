import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Character, int[]> dirs = new HashMap<>();
    static int N, M;
    static String[] arr;
    static int[][] parent;
    static boolean[][] visited;

    static int find(int x) {
        if (parent[x / M][x % M] == x) {
            return x;
        }
        return parent[x / M][x % M] = find(parent[x / M][x % M]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY / M][rootY % M] = rootX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dirs.put('U', new int[] {-1, 0});
        dirs.put('D', new int[] {1, 0});
        dirs.put('L', new int[] {0, -1});
        dirs.put('R', new int[] {0, 1});

        arr = new String[N];
        visited = new boolean[N][M];
        parent = new int[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            for (int j = 0; j < M; j++) {
                parent[i][j] = i * M + j;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int[] dir = dirs.get(arr[i].charAt(j));
                int ni = i + dir[0];
                int nj = j + dir[1];

                union(i * M + j, ni * M + nj);
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (find(i * M + j) == i * M + j) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}