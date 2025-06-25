import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        List<Integer>[] graph = new ArrayList[26];
        int[] indegree = new int[26];
        boolean[] isExist = new boolean[26];

        for (int i = 0; i < 26; i++) {
            graph[i] = new ArrayList<>();
        }

        for(String word: words) {
            for (char c : word.toCharArray()) {
                isExist[c - 'a'] = true;
            }
        }

        boolean hasConflict = false;
        for (int i = 0; i < N-1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            boolean found = false;

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if(c1 != c2) {
                    int from = c1 - 'a';
                    int to = c2 - 'a';

                    if(!graph[from].contains(to)) {
                        graph[from].add(to);
                        indegree[to]++;
                    }

                    found = true;
                    break;
                }
            }

            if(!found && w1.length() > w2.length()) {
                hasConflict = true;
                break;
            }
        }

        if(hasConflict) {
            System.out.println("!");
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean uncertain = false;

        for (int i = 0; i < 26; i++) {
            if(isExist[i] && indegree[i] == 0) q.add(i);
        }

        for (int i = 0; i < 26; i++) {
            if(q.isEmpty()) break;
            if(q.size() > 1) uncertain = true;

            int cur = q.poll();
            sb.append((char) (cur + 'a'));

            for (int nxt: graph[cur]) {
                indegree[nxt]--;
                if(indegree[nxt] == 0) q.add(nxt);
            }
        }

        int total = 0;
        for(boolean exist: isExist) {
            if(exist) total++;
        }

        if(sb.length() < total) {
            System.out.println("!");
        } else if (uncertain) {
            System.out.println("?");
        } else {
            System.out.println(sb.toString());
        }
    }


}