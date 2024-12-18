import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            String[] sLst = new String[n];
            for (int j = 0; j < n; j++) {
                sLst[j] = st.nextToken();
            }
            trie.insert(sLst);
        }

        trie.print(trie.root, -1);

        System.out.println(sb);
    }

    static class Node {
        String key;
        TreeMap<String, Node> child;
        boolean endOfWord;

        public Node(String key) {
            this.key = key;
            this.child = new TreeMap<>();
            this.endOfWord = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node(null);
        }

        void insert(String[] sLst) {
            Node node = this.root;

            for (String str : sLst) {
                node.child.putIfAbsent(str, new Node(str));

                node = node.child.get(str);
            }

            node.endOfWord = true;
        }

        void print(Node node, int depth) {
            if (node.key != null) {
                for (int i = 0; i < depth; i++) sb.append("--");
                sb.append(node.key).append("\n");
            }

            for (String key : node.child.keySet()) {
                Node child = node.child.get(key);
                print(child, depth + 1);
            }
        }
    }
}