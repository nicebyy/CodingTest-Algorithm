public class Solution {

    public int solution(String[] lines){
        int answer = 0;

        int[] cnt = new int[lines.length];
        double complete;

        for(int i = 0; i<lines.length;i++){

            lines[i] = lines[i].substring(11)
                    .replace(":","")
                    .replace("s","");
            
            int sec = Integer.parseInt(lines[i].substring(0,2))*3600 +
                    Integer.parseInt(lines[i].substring(2,4))*60+
                    Integer.parseInt(lines[i].substring(4,6));
            lines[i] = ""+ sec + lines[i].substring(6);
        }

        for(int i = 0; i< lines.length;i++){

            String[] split = lines[i].split("\\s");
            complete = Double.parseDouble(split[0]);
            double area = complete+1;

            for(int j = i;j< lines.length;j++){
                split = lines[j].split("\\s");
                double start = Double.parseDouble(split[0]) - Double.parseDouble(split[1]) + 0.001;
                if(start < area){
                    cnt[i]++;
                }
            }
        }

        for(int i = 0; i< cnt.length;i++){
            if(answer < cnt[i]) answer = cnt[i];
        }
        return answer;
    }
}