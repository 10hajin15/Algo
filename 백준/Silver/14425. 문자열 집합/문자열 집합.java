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

        int ans = 0;

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            trie.insert(str);
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if(trie.search(str)) ans++;
        }

        System.out.println(ans);
    }

    static class Node {
        Node[] child = new Node[26];
        boolean endOfWord;

        public Node() {}
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        void insert(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if(node.child[c-'a'] == null) {
                    node.child[c-'a'] = new Node();
                }

                node = node.child[c-'a'];
            }

            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if(node.child[c-'a'] == null) {
                    return false;
                } else {
                    node = node.child[c-'a'];
                }
            }

            return node.endOfWord;
        }
    }
}