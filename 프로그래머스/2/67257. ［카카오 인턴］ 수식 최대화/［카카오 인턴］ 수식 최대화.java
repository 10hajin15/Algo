import java.util.*;
import java.math.*;

class Solution {
    String[] oper = {"+", "-", "*"};
    List<String> exist = new ArrayList<>();
    boolean[] visited;
    long answer = 0;
    int num = 0;
    List<Long> numLst = new ArrayList<>();
    List<String> opLst = new ArrayList<>();

    long calc(String[] tLst) {
        List<Long> tNumLst = new ArrayList<>(numLst);
        List<String> tOpLst = new ArrayList<>(opLst);

        for (String op : tLst) {
            for (int i = 0; i < tOpLst.size(); ) {
                if (tOpLst.get(i).equals(op)) {
                    long res = 0;
                    long a = tNumLst.get(i);
                    long b = tNumLst.get(i + 1);
                    switch (op) {
                        case "+":
                            res = a + b;
                            break;
                        case "-":
                            res = a - b;
                            break;
                        case "*":
                            res = a * b;
                            break;
                    }
                    tNumLst.remove(i + 1);
                    tNumLst.set(i, res);
                    tOpLst.remove(i);
                } else {
                    i++;
                }
            }
        }
        return Math.abs(tNumLst.get(0));
    }

    void dfs(String[] tLst, int n) {
        if (n == num) {
            answer = Math.max(calc(tLst), answer);
            return;
        }

        for (int i = 0; i < num; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tLst[n] = exist.get(i);
                dfs(tLst, n + 1);
                visited[i] = false;
            }
        }
    }

    public long solution(String expression) {
        int idx = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                opLst.add(Character.toString(ch));
                numLst.add(Long.parseLong(expression.substring(idx, i)));
                idx = i + 1;
            }
        }
        numLst.add(Long.parseLong(expression.substring(idx)));

        for (String op : oper) {
            if (expression.contains(op)) exist.add(op);
        }

        num = exist.size();
        visited = new boolean[num];
        dfs(new String[num], 0);

        return answer;
    }
}
