import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Shark> sharks = new ArrayList<>();
        Shark[][] map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(r, c, s, d, z);

            map[r][c] = shark;
            sharks.add(shark);
        }

        int ans = 0;

        for (int j = 0; j < C; j++) {
            // 1. 상어 잡기
            for (int i = 0; i < R; i++) {
                if(map[i][j] != null) {
                    ans += map[i][j].size;
                    sharks.remove(map[i][j]);
                    map[i][j] = null;
                    break;
                }
            }

            Shark[][] newMap = new Shark[R][C];

            // 2. 상어 이동
            for(Shark shark: sharks) {
                int r = shark.r, c = shark.c, s = shark.speed, d = shark.dir;

                if (d < 2) s %= (R - 1) * 2;
                else s %= (C - 1) * 2;

                for (int i = 0; i < s; i++) {
                    int nr = r + dirs[d][0];
                    int nc = c + dirs[d][1];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                        d = (d % 2 == 0) ? d + 1 : d - 1;
                        nr = r + dirs[d][0];
                        nc = c + dirs[d][1];
                    }
                    r = nr;
                    c = nc;
                }

                shark.r = r;
                shark.c = c;
                shark.dir = d;

                if (newMap[r][c] == null || newMap[r][c].size < shark.size) {
                    newMap[r][c] = shark;
                }
            }

            map = newMap;
            sharks.clear();
            for (int i = 0; i < R; i++) {
                for (int k = 0; k < C; k++) {
                    if (map[i][k] != null) {
                        sharks.add(map[i][k]);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static class Shark {
        int r, c, speed, dir, size;

        public Shark(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}