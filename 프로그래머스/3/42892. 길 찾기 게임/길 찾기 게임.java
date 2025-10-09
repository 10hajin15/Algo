import java.util.*;

class Solution {
    void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    void preorder(Node node, List<Integer> list) {
        if (node == null) return;
        list.add(node.n);
        preorder(node.left, list);
        preorder(node.right, list);
    }
    
    void postorder(Node node, List<Integer> list) {
        if (node == null) return;
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.n);
    }
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });
        
        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            insert(root, nodes[i]);
        }
        
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        
        preorder(root, pre);
        postorder(root, post);
        
        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        return answer;
    }
    
    class Node {
        int n, x, y;
        Node left, right;
        Node(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }
}
