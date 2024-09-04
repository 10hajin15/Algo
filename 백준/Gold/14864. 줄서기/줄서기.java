import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] exist = new int[N+1];

        for(int i=1; i<=N; i++) {
            arr[i] = i;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x] += 1;
            arr[y] -= 1;
        }

        boolean flag = true;
        for(int i=1; i<=N; i++) {
            if(exist[arr[i]]==1) {
                flag = false;
                break;
            }
            exist[arr[i]] = 1;
        }

        if(!flag) {
            System.out.println(-1);
        } else {
            for(int i=1;i<=N; i++) {
                System.out.print(arr[i]+" ");
            }
        }
    }
}