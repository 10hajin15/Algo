import java.util.*;

class Solution {
    static Map<String, List<Integer>> map = new HashMap<>();

    private void makeCombination(String[] arr, int depth, String key, int score) {
        if (depth == 4) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }
        makeCombination(arr, depth + 1, key + arr[depth], score);
        makeCombination(arr, depth + 1, key + "-", score);
    }

    private int binarySearch(List<Integer> list, int score) {
        int left = 0, right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= score) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.size() - left;
    }

    public int[] solution(String[] info, String[] query) {
        for (String i : info) {
            String[] arr = i.split(" ");
            int score = Integer.parseInt(arr[4]);
            makeCombination(arr, 0, "", score);
        }

        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }

        List<Integer> answer = new ArrayList<>();
        for (String q : query) {
            String[] parts = q.replaceAll(" and ", " ").split(" ");
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int score = Integer.parseInt(parts[4]);

            if (!map.containsKey(key)) {
                answer.add(0);
            } else {
                List<Integer> list = map.get(key);
                answer.add(binarySearch(list, score));
            }
        }

        int[] ans = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        
        return ans;
    }
}
