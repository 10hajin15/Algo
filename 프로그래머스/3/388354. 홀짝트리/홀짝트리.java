import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[1000001];
        
        int[] answer = {0, 0};

        for (int node : nodes) {
            graph.put(node, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int node : nodes) {
            if (!visited[node]) {
                int[] result = bfs(node, graph, visited);
                if (result[0] == 1) answer[0]++;
                if (result[1] == 1) answer[1]++;
            }
        }

        return answer;
    }

    private int[] bfs(int start, Map<Integer, List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        boolean isOddEvenTree = true;
        boolean isReverseOddEvenTree = true;
        int oddEvenRootChance = 1;
        int reverseOddEvenRootChance = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int childCountIfRoot = graph.get(node).size();
            int childCountIfNotRoot = graph.get(node).size() - 1;

            if ((node + childCountIfNotRoot) % 2 == 1) {
                if (oddEvenRootChance > 0) {
                    oddEvenRootChance--;
                    if ((node + childCountIfRoot) % 2 == 1) {
                        isOddEvenTree = false;
                    }
                } else {
                    isOddEvenTree = false;
                }
            }

            if ((node + childCountIfNotRoot) % 2 == 0) {
                if (reverseOddEvenRootChance > 0) {
                    reverseOddEvenRootChance--;
                    if ((node + childCountIfRoot) % 2 == 0) {
                        isReverseOddEvenTree = false;
                    }
                } else {
                    isReverseOddEvenTree = false;
                }
            }

            for (int nextNode : graph.get(node)) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }

        if (isOddEvenTree && oddEvenRootChance > 0) isOddEvenTree = false;
        if (isReverseOddEvenTree && reverseOddEvenRootChance > 0) isReverseOddEvenTree = false;

        return new int[]{isOddEvenTree ? 1 : 0, isReverseOddEvenTree ? 1 : 0};
    }
}
