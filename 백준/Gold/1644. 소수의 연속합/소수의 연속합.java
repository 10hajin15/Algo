import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean isPrime(int n) {
        if(n <= 1) return false;
        for(int i=2; i<= Math.sqrt(n); i++) {
            if(n%i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> lst = new ArrayList<>();

        for (int i = 1; i < N+1; i++) {
            if(isPrime(i)) lst.add(i);
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