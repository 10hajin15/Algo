import java.util.*;

class Solution {    
    boolean calc(String str, int start, int end) {
        int len = end-start+1;
        
        for(int i=0; i<len/2; i++) {
            if(str.charAt(start+i) != str.charAt(end-i)) return false;
        }
        
        return true;
    }
    
    public int solution(String s) {
        int answer = 1;
        
        loop:
        for(int len = s.length()-1; len > 0; len--) {
            for(int i=0; i+len < s.length(); i++) {
                if(calc(s, i, i+len)) {
                    answer = len+1;
                    break loop;
                }
            }
        }
        
        return answer;
    }
}