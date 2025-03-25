import java.util.*;

class Solution {
    int N = 1000000;
    int[] indegree, outdegree;
    List<Integer>[] graph;
    int[] answer;
    int node;
    boolean[] visited;

    void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        int nCnt = 0;
        int eCnt = 0;

        while(!q.isEmpty()) {
            int now = q.poll();
            nCnt++;

            for(int nxt: graph[now]) {
                eCnt++;
                if(!visited[nxt]) {
                    visited[nxt] = true;
                    q.add(nxt);
                }
            }
        }

        if(nCnt == eCnt) {
            answer[1]++;
        } else if(nCnt == eCnt+1) {
            answer[2]++;
        } else {
            answer[3]++;
        }
    }

    public int[] solution(int[][] edges) {
        indegree = new int[N+1];
        outdegree = new int[N+1];
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        answer = new int[4];

        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList();
        }

        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];

            outdegree[a]++;
            indegree[b]++;
            graph[a].add(b);
        }

        for (int i = 1; i < N+1; i++) {
            if(indegree[i] == 0 && outdegree[i] >= 2) {
                answer[0] = i;
                node = i;
                visited[i] = true;
                break;
            }
        }

        for(int nxt: graph[node]) {
            if(visited[nxt]) continue;
            bfs(nxt);
        }

        return answer;
    }
}