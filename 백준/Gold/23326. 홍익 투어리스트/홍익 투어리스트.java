import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N;
    static TreeSet<Integer> landmarks;
    static int dohun;

    static int move() {
        if (landmarks.isEmpty()) return -1;

        Integer next = landmarks.ceiling(dohun);
        if (next == null) next = landmarks.first();

        return (next - dohun + N) % N;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        landmarks = new TreeSet<>();
        dohun = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int isLandmark = Integer.parseInt(st.nextToken());
            if (isLandmark == 1) {
                landmarks.add(i);
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if(query == 1) {
                int num = Integer.parseInt(st.nextToken())-1;

                if (landmarks.contains(num)) {
                    landmarks.remove(num);
                } else {
                    landmarks.add(num);
                }

            } else if (query == 2) {
                int x = Integer.parseInt(st.nextToken());

                dohun = (dohun + x) % N;
            } else {
                sb.append(move()).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}