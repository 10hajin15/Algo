import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = -1;
        for (int t = 0; t <= 100; t++) {
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] == k) {
                ans = t;
                break;
            }

            if (arr.length >= arr[0].length) {
                arr = rOper(arr);
            } else {
                arr = cOper(arr);
            }
        }

        System.out.println(ans);
    }

    private static int[][] rOper(int[][] arr) {
        int maxCol = 0;
        List<List<Integer>> newRows = new ArrayList<>();

        for (int[] row : arr) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : row) {
                if (num == 0) continue;
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }

            List<int[]> countList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                countList.add(new int[]{entry.getKey(), entry.getValue()});
            }
            countList.sort((a, b) -> a[1] == b[1] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

            List<Integer> newRow = new ArrayList<>();
            for (int[] pair : countList) {
                newRow.add(pair[0]);
                newRow.add(pair[1]);
            }
            newRows.add(newRow);
            maxCol = Math.max(maxCol, newRow.size());
        }

        maxCol = Math.min(maxCol, 100);
        int[][] newArr = new int[arr.length][maxCol];
        for (int i = 0; i < newRows.size(); i++) {
            List<Integer> row = newRows.get(i);
            for (int j = 0; j < row.size() && j < maxCol; j++) {
                newArr[i][j] = row.get(j);
            }
        }
        return newArr;
    }

    private static int[][] cOper(int[][] arr) {
        int[][] tmp = transpose(arr);

        int[][] ntmp = rOper(tmp);

        return transpose(ntmp);
    }

    private static int[][] transpose(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] transposed = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposed[j][i] = arr[i][j];
            }
        }
        return transposed;
    }
}