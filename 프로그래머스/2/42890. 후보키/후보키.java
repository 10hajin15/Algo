import java.util.*;

class Solution {
    static String[][] relation;
    static int rowLen, colLen;
    static List<List<Integer>> keys;
    static int answer;
    
    static void calc(List<Integer> cols) {
        Set<String> seen = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int col : cols) {
                sb.append(row[col]).append(" ");
            }
            seen.add(sb.toString());
        }

        if (seen.size() != rowLen) return;

        for (List<Integer> key : keys) {
            if (cols.containsAll(key)) return;
        }

        keys.add(new ArrayList<>(cols));
        answer++;
    }
    
    void comb(int start, int size, List<Integer> path) {
        if(path.size() == size) {
            calc(path);
            return;
        }
        
        for(int i=start; i<colLen; i++) {
            path.add(i);
            comb(i+1, size, path);
            path.remove(path.size()-1);
        }
    }
    
    public int solution(String[][] relation) {
        Solution.relation = relation;
        rowLen = relation.length;
        colLen = relation[0].length;
        keys = new ArrayList<>();
        answer = 0;
        
        for(int size=1; size<=colLen; size++) {
            comb(0, size, new ArrayList<>());
        }
        
        return answer;
    }
}