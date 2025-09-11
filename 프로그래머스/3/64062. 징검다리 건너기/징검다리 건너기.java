class Solution {
    static int answer;
    
    boolean isValid(int[] stones, int k, int mid) {
        int cnt = 0;

            for(int i=0; i<stones.length; i++) {
                if(cnt == k) {
                    return false;
                }
                if(stones[i] >= mid) {
                    cnt = 0;
                } else {
                    cnt++;
                }
            }

            if(cnt == k) return false;

            answer = Math.max(answer, mid);
            return true;
    }
    
    public int solution(int[] stones, int k) {
        answer = 0;
        
        

            int left = 1;
            int right = Integer.MIN_VALUE;

            for(int i=0; i<stones.length; i++) {
                right = Math.max(right, stones[i]);
            }
        
        if(stones.length == 1) return right;
        
        if(right == k) return k;

            while(left < right) {
                int mid = (left + right) / 2;

                if(isValid(stones, k, mid)) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }

            return answer;
    }
}