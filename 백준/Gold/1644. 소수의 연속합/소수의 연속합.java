import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean[] isPrime;

    static void eratosthenes(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(isPrime[i]) continue;

            for (int j = i * i; j < n+1; j = j + i) {
                isPrime[j] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        isPrime = new boolean[N + 1];
        eratosthenes(N);

        List<Integer> lst = new ArrayList<>();

        for (int i = 2; i < N+1; i++) {
            if(!isPrime[i]) lst.add(i);
        }

        int answer = 0;
        int end = 0;
        int sum = 0;
        for (int start = 0; start < lst.size(); start++) {
            while(sum <= N && end < lst.size()) {
                sum += lst.get(end);
                if(sum == N) answer++;
                end++;
            }
            sum -= lst.get(start);
            if(sum == N) answer++;
        }

        System.out.println(answer);
    }
}