import java.util.*;

class Solution {
    static int N;
    static String[] sMinerals;
    static int answer;
    static Map<String, Integer> dia;
    static Map<String, Integer> iron;
    static Map<String, Integer> stone;
    
    int calc(int[] tmp) {
        int tired = 0;
        int pIdx = 0;

        for (int i = 0; i < tmp.length; i++) {
            int type = tmp[i];
            Map<String, Integer> pickMap = (type == 0) ? dia : (type == 1) ? iron : stone;

            for (int j = 0; j < 5 && pIdx < sMinerals.length; j++) {
                tired += pickMap.get(sMinerals[pIdx++]);
            }
        }

        return tired;
    }



    void dfs(int depth, int[] tmp, int[] picks) {
        if (depth == tmp.length) {
            answer = Math.min(answer, calc(tmp));
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                tmp[depth] = i;
                dfs(depth + 1, tmp, picks);
                picks[i]++;
            }
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        sMinerals = minerals;
        answer = Integer.MAX_VALUE;

        dia = Map.of("diamond", 1, "iron", 1, "stone", 1);
        iron = Map.of("diamond", 5, "iron", 1, "stone", 1);
        stone = Map.of("diamond", 25, "iron", 5, "stone", 1);

        int total = picks[0] + picks[1] + picks[2];
        dfs(0, new int[total], picks);

        return answer;
    }
}