import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String[] sLst;

    static boolean search(String str) {
        int left = 0;
        int right = sLst.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (sLst[mid].equals(str)) {
                return true;
            }

            if (sLst[mid].compareTo(str) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        sLst = new String[N];
        for (int i = 0; i < N; i++) {
            sLst[i] = br.readLine();
        }
        Arrays.sort(sLst);

        int ans = 0;
        for (int i = 0; i < M; i++) {
            if (search(br.readLine())) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}