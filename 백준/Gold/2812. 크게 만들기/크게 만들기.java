import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String num = br.readLine();

        ArrayList<Character> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            char c = num.charAt(i);

            while (!list.isEmpty() && K > 0 && list.get(list.size() - 1) < c) {
                list.remove(list.size() - 1);
                K--;
            }

            list.add(c);
        }

        while (K > 0) {
            list.remove(list.size() - 1);
            K--;
        }

        StringBuilder sb = new StringBuilder();
        for (char digit : list) {
            sb.append(digit);
        }

        System.out.println(sb.toString());
    }
}