class Solution {
    int N, M;
    int count, sum;
    int[] emoti;
    int[][] uLst;

    void calc(int[] lst) {
        int tCount = 0;
        int tSum = 0;

        int[] sLst = new int[N];

        for (int i = 0; i < M; i++) {
            int price = emoti[i] - (emoti[i] * lst[i] / 100);
            for (int j = 0; j < N; j++) {
                int[] u = uLst[j];
                if(u[0] > lst[i]) continue;
                sLst[j] += price;
            }
        }

        for (int i = 0; i < N; i++) {
            if(sLst[i] >= uLst[i][1]) tCount++;
            else tSum += sLst[i];
        }

        if(tCount >= count) {
            if(tCount == count && tSum < sum) return;
            count = tCount;
            sum = tSum;
        }
    }

    void dfs(int depth, int[] lst) {
        if(depth == M) {
            calc(lst);
            return;
        }

        for (int i = 10; i <= 40; i+=10) {
            lst[depth] = i;
            dfs(depth+1, lst);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        count = Integer.MIN_VALUE;
        sum = Integer.MIN_VALUE;

        N = users.length;
        M = emoticons.length;

        emoti = emoticons;
        uLst = users;

        dfs(0, new int[M]);

        return new int[] {count, sum};
    }
}