import java.util.*;

class Solution {
    public int solution(int N, int number) {        
        if (N == number) return 1;
        
        List<Set<Integer>> lst = new ArrayList<>();
        lst.add(null);
        lst.add(new HashSet<>());
        lst.get(1).add(N);
        
        for (int cnt = 2; cnt <= 8; cnt++) {
            Set<Integer> tlst = new HashSet<>();
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cnt; i++) sb.append(N);
            int num = Integer.parseInt(sb.toString());
            tlst.add(num);
            if (num == number) return cnt;
            
            for (int i = 1; i < cnt; i++) {
                Set<Integer> aLst = lst.get(i);
                Set<Integer> bLst = lst.get(cnt - i);
                
                for (int a : aLst) {
                    for (int b : bLst) {
                        tlst.add(a + b);
                        tlst.add(a - b);
                        tlst.add(a * b);
                        if (b != 0) tlst.add(a / b);
                    }
                }
            }
            
            if (tlst.contains(number)) return cnt;
            lst.add(tlst);
        }
        
        return -1;
    }
}
