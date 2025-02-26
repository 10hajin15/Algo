import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int SIZE = 10;
    static final int INF = Integer.MAX_VALUE;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            map[i] = br.readLine().toCharArray();

        }

        int answer = INF;
        char[][] tmp = new char[SIZE][SIZE];
        for (int i = 0; i < (1<<SIZE); i++) {
            deepCopy(map, tmp);

            int cnt = simulation(tmp, i, 0);

            if(isAllOff(tmp)) {
                answer = Math.min(answer, cnt);
            }
        }

        System.out.println(answer == INF ? -1 : answer);
    }

    static boolean isAllOff(char[][] tmp) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(tmp[i][j] == 'O') return false;
            }
        }

        return true;
    }

    static int simulation(char[][] tmp, int c, int cnt) {
        for (int i = 0; i < SIZE; i++) {
            if((c & (1<<i)) != 0) {
                lightSwitch(tmp, 0, i);
                cnt++;
            }
        }

        for (int i = 1; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(tmp[i-1][j] == 'O') {
                    lightSwitch(tmp, i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void lightSwitch(char[][] tmp, int i, int j) {
        tmp[i][j] = switchOnOff(tmp[i][j]);

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni < 0 || ni >= SIZE || nj < 0 || nj >= SIZE) continue;

            tmp[ni][nj] = switchOnOff(tmp[ni][nj]);
        }
    }

    static char switchOnOff(char c) {
        return c == '#' ? 'O' : '#';
    }

    static void deepCopy(char[][] map, char[][] tmp) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }
}
