import java.util.*;

class Solution {
    public static int bfs(String[] p) {
        List<int[]> start = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (p[i].charAt(j) == 'P') {
                    start.add(new int[]{i, j});
                }
            }
        }

        for (int[] s : start) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(s);

            int[][] visited = new int[5][5];
            int[][] distance = new int[5][5];
            visited[s[0]][s[1]] = 1;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int y = current[0];
                int x = current[1];

                int[] dx = {-1, 1, 0, 0};
                int[] dy = {0, 0, -1, 1};

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && visited[ny][nx] == 0) {
                        if (p[ny].charAt(nx) == 'O') {
                            queue.add(new int[]{ny, nx});
                            visited[ny][nx] = 1;
                            distance[ny][nx] = distance[y][x] + 1;
                        }

                        if (p[ny].charAt(nx) == 'P' && distance[y][x] <= 1) {
                            return 0;
                        }
                    }
                }
            }
        }

        return 1;
    }
    
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            answer[i] = bfs(places[i]);
        }

        return answer;
    }
}