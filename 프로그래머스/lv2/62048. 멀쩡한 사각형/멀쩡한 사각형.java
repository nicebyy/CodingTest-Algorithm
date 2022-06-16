class Solution {
    public long solution(int w, int h) {
        return (long) w *h-(w+h-gcd(w,h));
    }

    long gcd(long a,long b){
        long r;

        while (b!=0){

            r = a%b;
            a = b;
            b = r;
        }

        return a;
    }
}