import java.util.*;

class Solution {    
    public String[] solution(String[] record) {
        Map<String, String> words = new HashMap<>();
        words.put("Enter", "님이 들어왔습니다.");
        words.put("Leave", "님이 나갔습니다.");
    
        Map<String, String> map = new HashMap<>();
        
        List<String[]> results = new ArrayList<>();
        
        for(int i=0; i<record.length; i++) {
            String[] rec = record[i].split(" ");
            String query = rec[0];
            
            if(query.equals("Enter")) {
                map.put(rec[1], rec[2]);
                results.add(new String[] {"Enter", rec[1]});
            } else if(query.equals("Leave")) {
                results.add(new String[] {"Leave", rec[1]});
            } else {
                map.put(rec[1], rec[2]);
            }
        }
        
        String[] answer = new String[results.size()];
        
        for(int i=0; i<results.size(); i++) {
            String[] result = results.get(i);
            answer[i] = map.get(result[1]) + words.get(result[0]);
        }
        
        return answer;
    }
}