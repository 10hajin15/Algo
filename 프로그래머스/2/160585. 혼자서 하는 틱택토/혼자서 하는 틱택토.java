import java.util.*;

class Solution {
    static int[][][] dirs = {{{0,0},{0,1},{0,2}}, {{1,0},{1,1},{1,2}}, {{2,0},{2,1},{2,2}}, {{0,0},{1,1},{2,2}}, {{0,2},{1,1},{2,0}},{{0,0},{1,0},{2,0}}, {{0,1},{1,1},{2,1}}, {{0,2},{1,2},{2,2}}};
    
    boolean check(String[] tmp, char c) {
        for(int[][] dir: dirs) {
            boolean flag = true;
            for(int[] d: dir) {
                if(tmp[d[0]].charAt(d[1]) != c) {
                    flag = false;
                    break;
                }
            }
            if(flag) return true;
        }
        
        return false;
    }

    public int solution(String[] board) {

        int oCnt = 0;
        int xCnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    oCnt++;
                } else if (board[i].charAt(j) == 'X') {
                    xCnt++;
                }
            }
        }
        
        boolean oWin = check(board, 'O');
        boolean xWin = check(board, 'X');

        if (oCnt == 0 && xCnt == 0) return 1;
        if (xCnt > oCnt || oCnt-xCnt > 1) return 0;
        if(oWin && xWin) return 0;
        if(oWin && oCnt == xCnt+1) return 1;
        if(xWin && xCnt == oCnt) return 1;
        if(!oWin && !xWin) return 1;

        return 0;
    }
}