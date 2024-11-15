import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        Map<Integer, int[]> pointMap = new HashMap<>();
        for(int i=0; i<points.length; i++) {
            pointMap.put(i+1, points[i]);
        }
        
        Map<Integer, ArrayList<int[]>> timeToPositions = new HashMap<>();
        
        for(int[] route: routes) {
            int ci = pointMap.get(route[0])[0];
            int cj = pointMap.get(route[0])[1];
            int time = 0;
            timeToPositions.computeIfAbsent(time, k -> new ArrayList<>()).add(new int[] {ci, cj});
            time++;
            
            for(int i=1; i<route.length; i++) {
                int ni = pointMap.get(route[i])[0];
                int nj = pointMap.get(route[i])[1];
                
                while(ci != ni) {
                    ci += (ci < ni) ? 1 : -1;
                    timeToPositions.computeIfAbsent(time, k -> new ArrayList<>()).add(new int[] {ci, cj});
                    time++;
                }
                
                while(cj != nj) {
                    cj += (cj < nj) ? 1 : -1;
                    timeToPositions.computeIfAbsent(time, k -> new ArrayList<>()).add(new int[] {ci, cj});
                    time++;
                }
                
            }
            
        }
        
        int dangerCount = 0;
        for (ArrayList<int[]> positions : timeToPositions.values()) {
            System.out.println("==================");
            Set<String> uniquePositions = new HashSet<>();
            for (int[] pos : positions) {
                uniquePositions.add(pos[0] + "," + pos[1]);
            }
            
            for(int[] a: positions) {
                System.out.println(Arrays.toString(a));
            }
            
            dangerCount += positions.size() - uniquePositions.size();
            System.out.println("count: "+dangerCount);
        }

        return dangerCount;
    }
}