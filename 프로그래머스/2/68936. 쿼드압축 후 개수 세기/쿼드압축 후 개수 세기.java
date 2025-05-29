class Solution {
    static int zero;
    static int one;

    public int[] solution(int[][] arr) {
        zero = 0;
        one = 0;
        dfs(arr, 0, 0, arr.length);
        return new int[]{zero, one};
    }

    private void dfs(int[][] arr, int row, int col, int size) {
        if (isSame(arr, row, col, size)) {
            if (arr[row][col] == 0) zero++;
            else one++;
            return;
        }

        int half = size / 2;
        dfs(arr, row, col, half);
        dfs(arr, row, col + half, half);
        dfs(arr, row + half, col, half);
        dfs(arr, row + half, col + half, half);
    }

    private boolean isSame(int[][] arr, int row, int col, int size) {
        int val = arr[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != val) return false;
            }
        }
        return true;
    }
}
