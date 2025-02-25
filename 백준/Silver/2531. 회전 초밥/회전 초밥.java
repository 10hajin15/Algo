import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] dishes = new int[N];
        int[] kinds = new int[d+1];
        kinds[c]++;

        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        int answer = Integer.MIN_VALUE;
        int tmp = 1;
        for(int i=0; i<k; i++) {
            int dish = dishes[i];
            if(kinds[dish] == 0) tmp++;
            kinds[dish]++;
        }
        answer = Math.max(answer, tmp);

        for (int i = 1; i < N; i++) {
            int prev = dishes[i-1];
            kinds[prev]--;
            if(kinds[prev] == 0) tmp--;

            int end = (i+k-1) % N;
            int dish = dishes[end];
            if(kinds[dish] == 0) tmp++;
            kinds[dish]++;

            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);
    }
}
