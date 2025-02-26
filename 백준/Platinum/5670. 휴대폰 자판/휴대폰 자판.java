import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static class TrieNode{
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean terminal = false;
        int childCount = 0;

        TrieNode(){}

        public void insert(String word) {
            TrieNode trieNode = this;
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);

                if (!trieNode.childNode.containsKey(c)) {
                    trieNode.childNode.put(c, new TrieNode());
                    trieNode.childCount++;
                }
                trieNode = trieNode.childNode.get(c);
            }
            trieNode.terminal = true;
        }

        public int countKeyPresses(String word) {
            TrieNode trieNode = this;
            int pressCount = 1;

            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                TrieNode node = trieNode.childNode.get(c);

                if(i > 0) {
                    if (trieNode.terminal || trieNode.childCount > 1) {
                        pressCount++;
                    }
                }
                trieNode = node;
            }
            return pressCount;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine())!=null) {
            int N = Integer.parseInt(line);
            List<String> inputData = new ArrayList<>();
            TrieNode trie = new TrieNode();

            for(int i=0; i<N; i++) {
                String str = br.readLine();
                inputData.add(str);
                trie.insert(str);
            }

            double res = 0;
            for(String str : inputData) {
                res += trie.countKeyPresses(str);
            }
            System.out.printf("%.2f%n", res/N);
        }
    }
}