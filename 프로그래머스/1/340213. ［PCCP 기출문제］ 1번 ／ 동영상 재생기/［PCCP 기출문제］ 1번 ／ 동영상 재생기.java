import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] videoS = video_len.split(":");
        int vLen = (60 * Integer.parseInt(videoS[0])) + Integer.parseInt(videoS[1]);
        String[] posS = pos.split(":");
        int vPos = (60 * Integer.parseInt(posS[0])) + Integer.parseInt(posS[1]);
        String[] op_startS = op_start.split(":");
        int vOStart = (60 * Integer.parseInt(op_startS[0])) + Integer.parseInt(op_startS[1]);
        String[] op_endS = op_end.split(":");
        int vOEnd = (60 * Integer.parseInt(op_endS[0])) + Integer.parseInt(op_endS[1]);
        
        Map<String, Integer> map = new HashMap<>();
        map.put("prev", -10);
        map.put("next", 10);
        
        for(String command: commands) {
            if(vOStart<=vPos && vPos<vOEnd) vPos = vOEnd;
            
            vPos += map.get(command);
            if(vOStart<=vPos && vPos<vOEnd) vPos = vOEnd;
            
            if(vPos < 0) vPos = 0;
            else if(vPos > vLen) vPos = vLen;
        }
        
        StringBuilder answer = new StringBuilder();
        int hour = vPos/60;
        if(hour<10) answer.append(0);
        answer.append(hour).append(":");
        int second = vPos%60;
        if(second<10) answer.append(0);
        answer.append(second);
        
        return answer.toString();
    }
}