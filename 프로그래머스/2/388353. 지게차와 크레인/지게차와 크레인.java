import java.util.*;
class Solution {
        static int N, M;
        static char[][] arr;
        static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        void forklift(char c) {
            boolean[][] visited = new boolean[N+2][M+2];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {0,0});
            visited[0][0] = true;

            while(!q.isEmpty()) {
                int[] now = q.poll();
                int i = now[0];
                int j = now[1];

                for(int[] dir: dirs) {
                    int ni = i+dir[0];
                    int nj = j+dir[1];

                    if(ni<0 || ni>=N+2 || nj<0 || nj>=M+2) continue;
                    if(visited[ni][nj] || arr[ni][nj] != '.') continue;
                    visited[ni][nj] = true;
                    q.add(new int[] {ni, nj});
                }
            }

            List<int[]> removes = new ArrayList<>();
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < M+1; j++) {
                    if(arr[i][j] == c) {
                        for(int[] dir: dirs) {
                            int ni = i+dir[0];
                            int nj = j+dir[1];

                            if(visited[ni][nj]) {
                                removes.add(new int[] {i, j});
                                break;
                            }
                        }
                    }
                }
            }

            for(int[] remove: removes) {
                arr[remove[0]][remove[1]] = '.';
            }
        }

        void crane(char c) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < M+1; j++) {
                    if(arr[i][j] != c) continue;
                    arr[i][j] = '.';
                }
            }
        }

        public int solution(String[] storage, String[] requests) {
            N = storage.length;
            M = storage[0].length();
            arr = new char[N+2][M+2];

            for(int i=0; i<N+2; i++) {
                Arrays.fill(arr[i], '.');
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i+1][j+1] = storage[i].charAt(j);
                }
            }

            for (String request: requests) {
                char c = request.charAt(0);

                if(request.length() == 1) {
                    forklift(c);
                } else {
                    crane(c);
                }
            }

            int answer = 0;

            for(int i=1; i<N+1; i++) {
                for(int j=1; j<M+1; j++) {
                    if(arr[i][j] != '.') answer++;
                }
            }

            return answer;
        }
    }