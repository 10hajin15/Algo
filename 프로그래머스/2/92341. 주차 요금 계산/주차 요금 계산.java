import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0];
        int baseCost = fees[1];
        int divTime = fees[2];
        int divCost = fees[3];
        
        Map<String, Integer> inMap = new HashMap<>();
        Map<String, Integer> outMap = new HashMap<>();       
        
        for(String record: records) {
            String[] tmp = record.split(" ");
            String[] t = tmp[0].split(":");
            int hour = Integer.parseInt(t[0]);
            int minute = Integer.parseInt(t[1]);
            
            if(tmp[2].equals("IN")) {
                inMap.put(tmp[1], (hour*60)+minute);
            } else {
                int gap = (hour*60)+minute - inMap.get(tmp[1]);
                outMap.put(tmp[1], gap+outMap.getOrDefault(tmp[1], 0));
                inMap.remove(tmp[1]);
            }
        }
        
        for(String name: inMap.keySet()) {
            int out = 23*60 + 59;
            int gap = out - inMap.get(name);
            outMap.put(name, gap+outMap.getOrDefault(name, 0));
        }
        
        List<int[]> results = new ArrayList<>();
        for(String number: outMap.keySet()) {
            int cost = 0;
            int total = outMap.get(number);
            
            if(total <= baseTime) {
                cost += baseCost;
            } else {
                cost += baseCost + (int) Math.ceil((double)(total-baseTime)/(double)divTime)*divCost;
            }
            
            results.add(new int[] {Integer.parseInt(number), cost});
        }
        
        Collections.sort(results, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        
        int[] answer = new int[results.size()];
        for(int i=0; i<results.size(); i++) {
            answer[i] = results.get(i)[1];
        }
        
        return answer;
    }
}