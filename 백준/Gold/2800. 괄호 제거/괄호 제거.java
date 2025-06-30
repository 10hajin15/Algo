import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<int[]> brackets = new ArrayList<>();
    static Set<String> answer = new TreeSet<>();
    static String str;

    static void dfs(int n, boolean[] removed) {
        if (n == brackets.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if(!removed[i]) sb.append(str.charAt(i));
            }

            String tmp = sb.toString();
            if(!tmp.equals(str)) answer.add(tmp);

            return;
        }

        dfs(n + 1, removed);

        int[] pair = brackets.get(n);
        removed[pair[0]] = true;
        removed[pair[1]] = true;
        dfs(n + 1, removed);
        removed[pair[0]] = false;
        removed[pair[1]] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        int n = str.length();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int start = stack.pop();
                brackets.add(new int[]{start, i});
            }
        }

        dfs(0, new boolean[n]);

        for (String ans : answer) {
            System.out.println(ans);
        }
    }
}