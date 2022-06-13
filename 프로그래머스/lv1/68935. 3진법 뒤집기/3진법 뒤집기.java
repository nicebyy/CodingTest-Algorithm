class Solution {
    public int solution(int n) {

        StringBuilder builder = new StringBuilder();
        int answer = 0;

        while (n>0){
            int r = n % 3;
            n /= 3;
            builder.insert(0,r);
        }


        String[] split = builder.toString().split("");
        int start = 0;
        boolean zeroStream = true;
        for (String s : split) {
            if(s.equals("0")&&zeroStream)
                continue;
            else 
                zeroStream=false;
            if(!zeroStream){
                answer+= Integer.parseInt(s)*Math.pow(3,start);
                start++;   
            }
        }
        return answer;
    }
}