class Solution {
    static final int DIV = 10007;
    
    public int solution(int n, int[] tops) {
        int[] a = new int[n+1];
        int[] b = new int[n+1];
        
        a[1] = 1;
        if(tops[0] == 0) {
            b[1] = 2;
        } else {
            b[1] = 3;
        }
        
        for(int i=2; i<=n; i++) {
            a[i] = (a[i-1] + b[i-1]) % DIV;
            if(tops[i-1] == 0) {
                b[i] = (a[i-1] + 2 * b[i-1]) % DIV;
            } else {
                b[i] = (2 * a[i-1] + 3 * b[i-1]) % DIV;
            }
        }

        return (a[n] + b[n]) % DIV;
    }
}