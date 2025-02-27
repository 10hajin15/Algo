class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int N = schedules.length;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int sTime = schedules[i] + 10;

            int hour = sTime / 100;
            int minute = sTime % 100;
            if (minute >= 60) {
                hour += 1;
                minute -= 60;
            }
            sTime = hour * 100 + minute;

            int day = startday;
            boolean flag = true;
            for (int j = 0; j < 7; j++) {
                if (day > 7) day = 1;

                if (day <= 5) {
                    if (timelogs[i][j] > sTime) {
                        flag = false;
                        break;
                    }
                }
                day++;
            }
            if (flag) answer++;
        }
        return answer;
    }
}
