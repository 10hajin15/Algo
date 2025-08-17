import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Node root;
    static Map<String, Integer> count;

    static int insert(String str) {
        Node node = root;

        int idx = Integer.MAX_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            node.child.putIfAbsent(c, new Node());
            node = node.child.get(c);
            if(node.child.isEmpty() && !node.isEnd) idx = Math.min(idx, i);
        }

        node.isEnd = true;

        return idx;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        root = new Node();
        count = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            count.put(str, count.getOrDefault(str, 0) + 1);

            int idx = insert(str);

            if (count.get(str) == 1) {
                if(idx == Integer.MAX_VALUE) {
                    sb.append(str).append('\n');
                } else {
                    sb.append(str.substring(0, idx+1)).append('\n');
                }
            } else {
                sb.append(str).append(count.get(str)).append('\n');
            }

        }

        System.out.println(sb.toString());
    }

    static class Node {
        HashMap<Character, Node> child;
        boolean isEnd;

        Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }
}