import java.util.*;

class Solution {
    private static final int MAX = 102;
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        boolean[][] map = new boolean[MAX][MAX];
        boolean[][] border = new boolean[MAX][MAX];
        boolean[][] visited = new boolean[MAX][MAX];

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = true;
                }
            }

            for (int i = x1; i <= x2; i++) {
                border[i][y1] = true;
                border[i][y2] = true;
            }
            for (int j = y1; j <= y2; j++) {
                border[x1][j] = true;
                border[x2][j] = true;
            }
        }

        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;

            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    border[i][j] = false;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{characterX * 2, characterY * 2, 0});
        visited[characterX * 2][characterY * 2] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], distance = current[2];

            if (x == itemX * 2 && y == itemY * 2) {
                return distance / 2;
            }

            for (int[] direction : DIRECTIONS) {
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx >= 0 && ny >= 0 && nx < MAX && ny < MAX && border[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, distance + 1});
                }
            }
        }

        return -1;
    }
}
