import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> arr;

    static void insert(int num) {
        int N = arr.size();
        int start = 0;
        int end = N-1;

        while (true) {
            if(start > end) break;
            if(start >= N || end >= N) break;

            int mid = (start+end) / 2;

            if(arr.get(mid) == num) {
                arr.add(mid, num);
                return;
            }

            if(arr.get(mid) < num) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }

        arr.add(start, num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            insert(num);

            if(arr.size() % 2 == 0) {
                sb.append(arr.get(arr.size()/2-1));
            } else {
                sb.append(arr.get(arr.size()/2));
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}