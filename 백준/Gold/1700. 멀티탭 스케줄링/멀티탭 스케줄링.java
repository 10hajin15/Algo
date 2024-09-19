import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> plugged = new HashSet<>();
        int ans = 0;

        for (int i = 0; i < K; i++) {
            int current = order[i];

            if (plugged.contains(current)) {
                continue;
            }

            if (plugged.size() < N) {
                plugged.add(current);
                continue;
            }

            int itemToUnplug = -1;
            int latestUse = -1;

            for (int plug : plugged) {
                int nextUse = Integer.MAX_VALUE;

                for (int j = i + 1; j < K; j++) {
                    if (order[j] == plug) {
                        nextUse = j;
                        break;
                    }
                }

                if (nextUse > latestUse) {
                    latestUse = nextUse;
                    itemToUnplug = plug;
                }
            }

            plugged.remove(itemToUnplug);
            plugged.add(current);
            ans++;
        }

        System.out.println(ans);
    }
}