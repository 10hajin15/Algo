import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int N = A.length;
        
        int i = 0;
        int answer = 0;
        for(int j=0; j<N; j++) {
            while(i < N && B[i] <= A[j]) {
                i++;
            }
            if(i >= N) break;
            
            answer++;
            i++;
        }
        
        return answer;
    }
}