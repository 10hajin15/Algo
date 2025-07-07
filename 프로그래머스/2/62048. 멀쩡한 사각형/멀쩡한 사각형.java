class Solution {
    private long gcd(long a, long b) {
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }

    public long solution(int w, int h) {
        long gcd = gcd((long)w, (long)h);
        return (long)w * h - (w + h - gcd);
    }
}