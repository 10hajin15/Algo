import java.util.*;

class Solution {
    public int solution(int n, int w, int target) {
        int idx = -1;        
        int row = n/w;
        if(n % w != 0) row++;
        
        int[][] arr = new int[row][w];    
        int answer = 0;
        int num = 1;
        
        loop:
        for(int i=0; i<row; i++) {
            for(int j=0; j<w; j++) {
                int col = j;
                if(i % 2 != 0) col = w-j-1;
                
                if(idx != -1 && idx == col) {
                    answer++;
                }
                if(num == target) {
                    idx = col;
                }
                num++;
                if(num > n) break loop;
            }
        }
        return answer+1;
    }
}