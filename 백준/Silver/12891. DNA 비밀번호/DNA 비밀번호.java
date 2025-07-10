import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int isNum(char c) {
        if(c == 'A') return 0;
        else if(c == 'C') return 1;
        else if(c == 'G') return 2;
        else return 3;
    }

    static boolean isValid(int[] tmp, int[] count) {
        for (int i = 0; i < 4; i++) {
            if (tmp[i] < count[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] str = br.readLine().toCharArray();

        int[] count = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int[] tmp = new int[4];

        for (int i = 0; i < P; i++) {
            tmp[isNum(str[i])]++;
        }

        if (isValid(tmp, count)) answer++;

        for (int i = P; i < S; i++) {
            tmp[isNum(str[i - P])]--;
            tmp[isNum(str[i])]++;
            if (isValid(tmp, count)) answer++;
        }

        System.out.println(answer);
    }
}