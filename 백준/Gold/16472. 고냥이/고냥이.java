import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] cnts = new int[26];
        int answer = 0;

        int tmp = 0;
        int right = 0;
        for (int left = 0; left < str.length(); left++) {
            char c = str.charAt(left);

            while(right < str.length()) {
                char nxt = str.charAt(right);
                if(cnts[nxt-97] == 0) {
                    if(tmp == N) break;
                    tmp++;
                }
                cnts[nxt-97]++;
                right++;
            }

            answer = Math.max(answer, right - left);

            cnts[c-97]--;
            if(cnts[c-97] == 0) tmp--;

        }

        System.out.println(answer);
    }
}