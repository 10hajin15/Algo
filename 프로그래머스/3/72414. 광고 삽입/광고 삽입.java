class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSeconds = timeToSeconds(play_time);
        int advSeconds = timeToSeconds(adv_time);
        long[] timeline = new long[playSeconds + 2];

        for (String log : logs) {
            String[] parts = log.split("-");
            int start = timeToSeconds(parts[0]);
            int end = timeToSeconds(parts[1]);
            timeline[start] += 1;
            timeline[end] -= 1;
        }

        for (int i = 1; i <= playSeconds; i++) {
            timeline[i] += timeline[i - 1];
        }

        for (int i = 1; i <= playSeconds; i++) {
            timeline[i] += timeline[i - 1];
        }

        long maxView = timeline[advSeconds - 1];
        int startTime = 0;

        for (int i = advSeconds; i <= playSeconds; i++) {
            long currentView = timeline[i] - timeline[i - advSeconds];
            if (currentView > maxView) {
                maxView = currentView;
                startTime = i - advSeconds + 1;
            }
        }

        return secondsToTime(startTime);
    }

    int timeToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 3600
             + Integer.parseInt(parts[1]) * 60
             + Integer.parseInt(parts[2]);
    }

    String secondsToTime(int totalSeconds) {
        int h = totalSeconds / 3600;
        int m = (totalSeconds % 3600) / 60;
        int s = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}