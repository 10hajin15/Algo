class Solution {
    static int n, m;
    
    boolean isRange(int x, int y) {
        if(0<=x && x<n && 0<=y && y<m) return true;
        return false;
    }
    
    public int solution(int[][] board, int[][] skill) {
        n = board.length;
        m = board[0].length;
        
        int[][] result = new int[n][m];
        
        for(int[] sk: skill) {
            int type = sk[0];
            int r1 = sk[1]; int c1 = sk[2];
            int r2 = sk[3]; int c2 = sk[4];
            int degree = sk[5];
            
            if(type == 1) degree = -degree;
            
            result[r1][c1] += degree;
            if(isRange(r1, c2+1)) result[r1][c2+1] -= degree;
            if(isRange(r2+1, c1)) result[r2+1][c1] -= degree;
            if(isRange(r2+1, c2+1)) result[r2+1][c2+1] += degree;
        }
         
        for(int i=0; i<n; i++) {
            for(int j=1; j<m; j++) {
                result[i][j] += result[i][j-1];
            }
        }
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<m; j++) {
                result[i][j] += result[i-1][j];
            }
        }

        int answer = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i][j] += result[i][j];
                if(board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}