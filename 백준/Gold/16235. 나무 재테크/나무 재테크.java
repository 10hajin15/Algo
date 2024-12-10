import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[][] nutri;
    static int[][] map;
    static ArrayList<Integer>[][] tree;
    static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        nutri = new int[N][N];
        map = new int[N][N];
        tree = new ArrayList[N][N];

        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                nutri[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
                tree[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            tree[x][y].add(z);
            Collections.sort(tree[x][y]);
        }

        while(K-- > 0) {
            ArrayList<int[]> dieTree = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(tree[i][j].size() > 0) {
                        ArrayList<Integer> tlst = new ArrayList<>();
                        for (int k = 0; k < tree[i][j].size(); k++) {
                            int age = tree[i][j].get(k);
                            if (map[i][j] < age) dieTree.add(new int[] {i, j, age});
                            else {
                                map[i][j] -= age;
                                tlst.add(age+1);
                            }
                        }
                        tree[i][j] = tlst;
                    }
                }
            }

            for(int[] die: dieTree) {
                int x = die[0];
                int y = die[1];
                int age = die[2];
                map[x][y] += (age / 2);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(tree[i][j].size() > 0) {
                        for (int k = 0; k < tree[i][j].size(); k++) {
                            int age = tree[i][j].get(k);
                            if(age % 5 == 0) {
                                for (int l = 0; l < 8 ; l++) {
                                    int ni = i+dirs[l][0];
                                    int nj = j+dirs[l][1];
                                    if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;

                                    tree[ni][nj].add(0, 1);
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] += nutri[i][j];
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += tree[i][j].size();
            }
        }

        System.out.println(ans);
    }
}