import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) break;

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            int[] parent = new int[n];
            parent[0] = -1;

            int pIdx = -1;
            for (int i = 1; i < n; i++) {
                if (arr[i] != arr[i - 1] + 1) pIdx++;
                parent[i] = pIdx;
            }

            int kIdx = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] == k) {
                    kIdx = i;
                    break;
                }
            }

            int parentOfK = parent[kIdx];
            if (parentOfK == -1) {
                sb.append(0).append('\n');
                continue;
            }

            int grandParent = parent[parentOfK];

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (parent[i] == -1) continue;
                if (parent[parent[i]] == grandParent && parent[i] != parentOfK)
                    count++;
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb.toString());
    }
}
