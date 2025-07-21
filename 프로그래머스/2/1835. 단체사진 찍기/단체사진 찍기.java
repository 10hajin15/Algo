class Solution {
    static int N;
    static String[] datas;
    static int answer;
    static String[] names = {"A", "C", "F", "J", "M", "N", "R", "T"};
    
void calc(int[] lst) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<8; i++) {
                sb.append(names[lst[i]]);
            }

            String tmp = sb.toString();

            for(String data: datas) {
                char a = data.charAt(0);
                char b = data.charAt(2);
                char oper = data.charAt(3);
                int n1 = data.charAt(4) - '0';

                int idx1 = tmp.indexOf(a);
                int idx2 = tmp.indexOf(b);
                int diff = Math.abs(idx1-idx2)-1;

                if(oper == '=') {
                    if(diff != n1) {
                        return;
                    }
                } else if(oper == '<') {
                    if(diff >= n1) {
                        return;
                    }
                } else if(oper == '>') {
                    if(diff <= n1) {
                        return;
                    }
                }
            }

            answer++;
        }

        void dfs(int depth, boolean[] visited, int[] lst) {
            if(depth >= 8) {
                calc(lst);
                return;
            }

            for(int i=0; i<8; i++) {
                if(visited[i]) continue;
                visited[i] = true;
                lst[depth] = i;
                dfs(depth+1, visited, lst);
                visited[i] = false;
            }
        }


        public int solution(int n, String[] data) {
            N = n;
            datas = data;
            answer = 0;

            dfs(0, new boolean[8], new int[8]);

            return answer;
        }
}