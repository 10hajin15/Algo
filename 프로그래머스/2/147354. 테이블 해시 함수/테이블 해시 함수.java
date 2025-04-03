import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        List<Data> lst = new ArrayList<>();
        
        for(int[] row: data) {
            Data nData = new Data(row[col-1], row[0], row);
            lst.add(nData);
        }
        
        Collections.sort(lst);
        
        List<Integer> ans = new ArrayList<>();
        
        for(int s = row_begin; s <= row_end; s++) {
            Data d = lst.get(s-1);
            int[] dLst = d.nums;
                        
            int sum = 0;
            for (int i = 0; i < dLst.length; i++) {
                sum += (dLst[i] % s);
            }
            
            ans.add(sum);
        }
        
        int answer = ans.get(0);
        for (int i = 1; i < ans.size(); i++) {
            answer ^= ans.get(i);
        }

        return answer;
    }
    
    class Data implements Comparable<Data> {
        int targetNum, keyNum;
        int[] nums;
        
        Data(int targetNum, int keyNum, int[] nums) {
            this.targetNum = targetNum;
            this.keyNum = keyNum;
            this.nums = nums;
        }
        
        @Override
        public int compareTo(Data d) {
            if (this.targetNum != d.targetNum) {
                return Integer.compare(this.targetNum, d.targetNum);
            }
            return Integer.compare(-this.keyNum, -d.keyNum);
        }
    }
}