import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;
    static String[] words;
    static List<Character> lst = new ArrayList<>();
    static boolean[] visited = new boolean[26];

    static void dfs(int depth, int start) {
        if (depth == K - 5) {
            int count = 0;
            for (String word : words) {
                boolean canRead = true;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!visited[c - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) count++;
            }
            answer = Math.max(answer, count);
            return;
        }

        for (int i = start; i < lst.size(); i++) {
            char c = lst.get(i);
            visited[c - 'a'] = true;
            dfs(depth + 1, i + 1);
            visited[c - 'a'] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new String[N];
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
            for (char c : word.toCharArray()) {
                if ("antic".indexOf(c) == -1) {
                    set.add(c);
                }
            }
        }

        lst.addAll(set);

        if (lst.size() <= K - 5) {
            System.out.println(N);
            return;
        }

        for (char c : "antic".toCharArray()) {
            visited[c - 'a'] = true;
        }

        dfs(0, 0);
        System.out.println(answer);
    }
}