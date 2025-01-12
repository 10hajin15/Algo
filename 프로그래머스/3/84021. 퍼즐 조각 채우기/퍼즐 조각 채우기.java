import java.util.*;

class Solution {
    static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    
    List<List<int[]>> bfs(int[][] board, int target) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> shapes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == target) {
                    List<int[]> shape = new ArrayList<>();
                    
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        int[] current = q.poll();
                        shape.add(current);

                        for (int d = 0; d < 4; d++) {
                            int nx = current[0] + dirs[d][0];
                            int ny = current[1] + dirs[d][1];

                            if (nx >= 0 && ny >= 0 && nx < n && ny < n &&
                                !visited[nx][ny] && board[nx][ny] == target) {
                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }

                    normalizeShape(shape);
                    shapes.add(shape);
                }
            }
        }

        return shapes;
    }

    void normalizeShape(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] cell : shape) {
            minX = Math.min(minX, cell[0]);
            minY = Math.min(minY, cell[1]);
        }

        for (int[] cell : shape) {
            cell[0] -= minX;
            cell[1] -= minY;
        }

        shape.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    }

    boolean canFit(List<int[]> emptySpace, List<int[]> piece) {
        for (int rotation = 0; rotation < 4; rotation++) {
            if (match(emptySpace, piece)) {
                return true;
            }
            rotate(piece);
        }
        return false;
    }

    boolean match(List<int[]> emptySpace, List<int[]> piece) {
        if (emptySpace.size() != piece.size()) return false;

        for (int i = 0; i < emptySpace.size(); i++) {
            int[] spaceCell = emptySpace.get(i);
            int[] pieceCell = piece.get(i);

            if (spaceCell[0] != pieceCell[0] || spaceCell[1] != pieceCell[1]) {
                return false;
            }
        }

        return true;
    }

    void rotate(List<int[]> piece) {
        for (int[] cell : piece) {
            int temp = cell[0];
            cell[0] = cell[1];
            cell[1] = -temp;
        }

        normalizeShape(piece);
    }

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;

        List<List<int[]>> emptySpaces = bfs(game_board, 0);
        List<List<int[]>> puzzlePieces = bfs(table, 1);

        boolean[] used = new boolean[puzzlePieces.size()];
        int filledCells = 0;

        for (List<int[]> emptySpace : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                if (used[i]) continue;
                List<int[]> piece = puzzlePieces.get(i);

                if (canFit(emptySpace, piece)) {
                    filledCells += piece.size();
                    used[i] = true;
                    break;
                }
            }
        }

        return filledCells;
    }
}
