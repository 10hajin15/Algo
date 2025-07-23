import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long di, dj;

    public static void dfs(String str, int depth, long si, long ei, long sj, long ej, long len) {
        if (str.length() == depth) {
            if(si == ei && sj == ej) {
                di = si;
                dj = sj;
            }
            return;
        }

        int num = str.charAt(depth) - '0';

        if (num == 1) {
            dfs(str, depth+1, si, si+len/2-1, sj+len/2, ej, len/2);
        } else if (num == 2) {
            dfs(str, depth+1, si, si+len/2-1, sj, sj+len/2-1, len/2);
        } else if (num == 3) {
            dfs(str, depth + 1, si + len / 2, ei, sj, sj + len / 2 - 1, len / 2);
        } else {
            dfs(str, depth + 1, si + len / 2, ei, sj + len / 2, ej, len/2);
        }
    }

    public static String encode(long si, long sj, long ei, long ej, long i, long j, long len) {
        if(len == 1) return "";

        long mi = (si + ei) / 2;
        long mj = (sj + ej) / 2;

        if (i <= mi && j > mj) {
            return "1" + encode(si, mj + 1, mi, ej, i, j, len / 2);
        } else if (i <= mi && j <= mj) {
            return "2" + encode(si, sj, mi, mj, i, j, len / 2);
        } else if (i > mi && j <= mj) {
            return "3" + encode(mi + 1, sj, ei, mj, i, j, len / 2);
        } else {
            return "4" + encode(mi + 1, mj + 1, ei, ej, i, j, len / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        String dn = st.nextToken();

        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        long t = (long) Math.pow(2, dn.length());
        dfs(dn, 0, 0, t-1, 0, t-1, t);

        long ni = di - y;
        long nj = dj + x;

        if (ni < 0 || ni >= t || nj < 0 || nj >= t) {
            System.out.println(-1);
        } else {
            String result = encode(0, 0, t - 1, t - 1, ni, nj, t);
            System.out.println(result);
        }

    }
}