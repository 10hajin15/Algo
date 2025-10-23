import java.util.*;

class Solution {
    class Node {
        Map<Character, Node> child = new HashMap<>();
        int pass = 0;
    }
    
    class Trie {
        Node root;
        
        Trie() {
            this.root = new Node();
        }
        
        void insert(String str) {
            Node node = this.root;
            
            for(int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
                node.pass++;
            }
        }
        
        int search(String str) {
            Node node = this.root;
            int total = 1;
            
            for(int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                
                if(i > 0 && node.pass > 1) {
                    total++;
                }
                
                node = node.child.get(c);
            }
            
            return total;
        }
    }
    
    
    public int solution(String[] words) {
        Trie trie = new Trie();
        
        for(String word: words) {
            trie.insert(word);
        }
        
        int answer = 0;
        for(String word: words) {
            int s = trie.search(word);
            answer += s;
        }
        
        return answer;
    }
}