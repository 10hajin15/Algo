import java.util.*;

class Solution {
    public int solution(String[] lines) {
        double[] start = new double[lines.length];
        double[] end = new double[lines.length];
        
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            String[] t = parts[1].split(":");
            double duration = Double.parseDouble(parts[2].replace("s", ""));
            
            double sec = Double.parseDouble(t[0]) * 3600 +
                         Double.parseDouble(t[1]) * 60 +
                         Double.parseDouble(t[2]);
            
            end[i] = sec;
            start[i] = sec - duration + 0.001;
        }

        int answer = 0;
        for (int i = 0; i < lines.length; i++) {
            double rangeStart = end[i];
            double rangeEnd = end[i] + 1.0 - 0.000001;
            
            int count = 0;
            for (int j = 0; j < lines.length; j++) {
                if (start[j] <= rangeEnd && end[j] >= rangeStart) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
        }

        return answer;
    }
}
