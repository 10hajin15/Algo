class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
                int answer = 0;

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for(int i=0; i<diffs.length; i++) {
            left = Math.min(left, diffs[i]);
            right = Math.max(right, diffs[i]);
        }

        while(true) {
            if(left > right) {
                answer = left
                ;
                break;
            }

            int mid = (left+right)/2;

            long time = 0;
            for(int i=0; i<diffs.length; i++) {
                long add = 0;
                if(diffs[i] <= mid) {
                    add = times[i];
                } else {
                    add = ((times[i]+times[i-1]) * (diffs[i]-mid) + times[i]);
                }
                time += add;
            }

            if(time <= limit) right = mid-1;
            else left = mid+1;

        }

        return answer;
    }
}