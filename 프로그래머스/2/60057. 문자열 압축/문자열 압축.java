class Solution {
    public int solution(String s) {
        int slen = s.length();
        
        int answer = slen;
        
        if(slen == 1) return 1; 
        
        for(int len=1; len<=slen/2; len++) {
            String pattern = s.substring(0, len);
            int cnt = 1;
            StringBuilder reStr = new StringBuilder();
            for(int i=len; i<=slen-len; i+=len) {
                if(pattern.equals(s.substring(i, i+len))) {
                    cnt++;
                } else {
                    if(cnt > 1) {
                        reStr.append(cnt);
                    }
                    reStr.append(pattern);
                    pattern = s.substring(i, i+len);
                    cnt = 1;
                }
            }
            
            if(cnt > 1) {
                reStr.append(cnt);
            }
            reStr.append(pattern);
            
            int div = slen % len;
            answer = Math.min(answer, reStr.length() + div);
        }
        
        return answer;
    }
}