class Solution {
    int[] apeach;
    int diff;
    int[] lst;

    int calc(int[] tlst) {
        int aScore = 0;
        int lScore = 0;

        for (int i = 0; i <= 10; i++) {
            int score = 10 - i;
            if (apeach[i] == 0 && tlst[i] == 0) continue;
            if (apeach[i] >= tlst[i]) aScore += score;
            else lScore += score;
        }


        return lScore - aScore;
    }

    void dfs(int depth, int cnt, int[] tlst) {
        if(depth == 11 || cnt == 0) {
            if(cnt > 0) tlst[10] += cnt;

            int tdiff = calc(tlst);
            if(tdiff > diff) {
                diff = tdiff;
                lst = tlst.clone();
            } else if (tdiff == diff) {
                for (int i = 10; i >= 0 ; i--) {
                    if(tlst[i] > lst[i]) {
                        lst = tlst.clone();
                        break;
                    } else if(tlst[i] < lst[i]) break;
                }
            }

            if(cnt > 0) tlst[10] -= cnt;
            return;
        }

        if (cnt > apeach[depth]) {
            tlst[depth] = apeach[depth] + 1;
            dfs(depth + 1, cnt - (apeach[depth] + 1), tlst);
            tlst[depth] = 0;
        }

        dfs(depth + 1, cnt, tlst);
    }
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        diff = 0;
        lst = new int[11];

        dfs(0, n, new int[11]);

        if(diff <= 0) return new int[]{-1};

        return lst;
    }
}