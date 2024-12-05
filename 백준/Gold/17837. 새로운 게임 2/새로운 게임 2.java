import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{0,1}, {0,-1}, {-1,0},{1,0}};
    static int N, K;
    static int[][] board;
    static ArrayList<Integer>[][] pieces;
    static int[][] pieceInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        pieces = new ArrayList[N][N];
        pieceInfo = new int[K][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                pieces[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;

            pieceInfo[i] = new int[] {x, y, dir};
            pieces[x][y].add(i);
        }

        int cnt = 0;

        while(cnt++ < 1000) {
            for (int i = 0; i < K; i++) {
                if(movePiece(i)) {
                    System.out.println(cnt);
                    return;
                }
            }

        }
        System.out.println(-1);
    }

    static boolean movePiece(int idx) {
        int si = pieceInfo[idx][0];
        int sj = pieceInfo[idx][1];
        int dir = pieceInfo[idx][2];

        int ni = si + dirs[dir][0];
        int nj = sj + dirs[dir][1];

        if(!isValid(ni, nj) || board[ni][nj] == 2) {
            pieceInfo[idx][2] = dir = (dir % 2 == 0) ? dir + 1 : dir -1;
            ni = si + dirs[dir][0];
            nj = sj + dirs[dir][1];

            if(!isValid(ni, nj) || board[ni][nj] == 2) return false;
        }

        ArrayList<Integer> movingPieces = splitPieces(si, sj, idx);
        if(board[ni][nj] == 1) {
            reversePieces(movingPieces);
        }

        pieces[ni][nj].addAll(movingPieces);
        for(int p : movingPieces) {
            pieceInfo[p][0] = ni;
            pieceInfo[p][1] = nj;
        }

        return pieces[ni][nj].size() >= 4;
    }

    private static ArrayList<Integer> splitPieces(int si, int sj, int idx) {
        ArrayList<Integer> stack = pieces[si][sj];
        ArrayList<Integer> movingPieces = new ArrayList<>();
        int start = stack.indexOf(idx);

        for (int i = start; i < stack.size(); i++) {
            movingPieces.add(stack.get(i));
        }

        for (int i = stack.size()-1; i >= start; i--) {
            stack.remove(i);
        }

        return movingPieces;
    }

    private static void reversePieces(ArrayList<Integer> movingPieces) {
        int size = movingPieces.size();
        for (int i = 0; i < size/2; i++) {
            int tmp = movingPieces.get(i);
            movingPieces.set(i, movingPieces.get(size-1-i));
            movingPieces.set(size-1-i, tmp);
        }
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
}