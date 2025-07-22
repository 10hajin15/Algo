class Solution {
    public int[] solution(int[] sequence, int k) {
        int len = sequence.length;
        
        int[] answer = {len, len};
        
        int sum = 0;
        int diff = len;
        
        int right = 0;
        
        for(int left=0; left < len; left++) {
            while(sum < k) {
                if(right >= len) break;
                sum += sequence[right];
                right++;
            }
            
            if(sum == k) {
                if(right-left < diff) {
                    answer[0] = left;
                    answer[1] = right-1;
                    diff = right-left;
                } else if(right-left == diff) {
                    if(left < answer[0]) {
                        answer[0] = left;
                        answer[1] = right-1;
                    }
                }
            }
            
            sum -= sequence[left];
        } 
        
        return answer;
    }
}