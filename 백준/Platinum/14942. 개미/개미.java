import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, D = 20;
    static int[] energy;
    static ArrayList<Edge>[] tree;
    static int[][] parents;  // 부모 노드와 그까지의 비용 저장
    static int[][] dist;     // 부모 노드까지의 거리
    static int[] result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 초기화
        energy = new int[N];
        tree = new ArrayList[N];
        parents = new int[N][D];
        dist = new int[N][D];
        result = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            energy[i] = Integer.parseInt(br.readLine());
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new Edge(b, c));
            tree[b].add(new Edge(a, c));
        }

        // 0번 방을 기준으로 DFS를 시작하여 부모 관계와 거리를 설정
        visited[0] = true;
        dfs(0);

        // 부모 테이블 초기화 (2^i번째 부모를 설정)
        parentInit();

        // 각 개미에 대해 에너지를 기준으로 도달할 수 있는 가장 가까운 방을 계산
        for (int i = 0; i < N; i++) {
            result[i] = search(i, i, energy[i]);
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.println(result[i]);
        }
    }

    // DFS를 통해 부모 관계와 1번째 부모까지의 거리를 설정
    static void dfs(int node) {
        for (Edge edge : tree[node]) {
            int nextNode = edge.to;
            int cost = edge.cost;
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                parents[nextNode][0] = node;
                dist[nextNode][0] = cost;
                dfs(nextNode);
            }
        }
    }

    // 부모 테이블 초기화 (LCA 전처리)
    static void parentInit() {
        for (int d = 1; d < D; d++) {
            for (int node = 0; node < N; node++) {
                int pNode = parents[node][d - 1];  // 부모 노드
                parents[node][d] = parents[pNode][d - 1];  // 부모의 부모
                dist[node][d] = dist[node][d - 1] + dist[pNode][d - 1];  // 비용 합산
            }
        }
    }

    // 에너지를 기준으로 가장 가까운 방을 찾는 함수
    static int search(int idx, int cur, int leftEnergy) {
        if (cur == 0) {  // 1번 방(0번 인덱스)까지 도달한 경우
            return 1;
        }

        // LCA 방식으로 부모를 찾아가며 탐색
        for (int d = D - 1; d >= 0; d--) {
            if (dist[cur][d] <= leftEnergy) {  // 남은 에너지 내에서 부모로 이동 가능한 경우
                return search(idx, parents[cur][d], leftEnergy - dist[cur][d]);
            }
        }

        // 더 이상 이동할 수 없는 경우 현재 방에서 멈춤
        return cur + 1;
    }
}