import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            char[][] board = new char[3][3];
            int xCnt = 0, oCnt = 0;

            for (int i = 0; i < 9; i++) {
                char ch = str.charAt(i);
                board[i / 3][i % 3] = ch;
                if (ch == 'X') xCnt++;
                else if (ch == 'O') oCnt++;
            }

            boolean xWin = isWin(board, 'X');
            boolean oWin = isWin(board, 'O');

            if (oCnt > xCnt || xCnt - oCnt > 1) {
                sb.append("invalid\n");
                continue;
            }

            if (xWin && oWin) {
                sb.append("invalid\n");
                continue;
            }

            if (xWin && xCnt == oCnt + 1) {
                sb.append("valid\n");
                continue;
            }

            if (oWin && xCnt == oCnt) {
                sb.append("valid\n");
                continue;
            }

            if (!xWin && !oWin && xCnt + oCnt == 9) {
                sb.append("valid\n");
                continue;
            }

            sb.append("invalid\n");
        }

        System.out.println(sb);
    }

    static boolean isWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }
}
