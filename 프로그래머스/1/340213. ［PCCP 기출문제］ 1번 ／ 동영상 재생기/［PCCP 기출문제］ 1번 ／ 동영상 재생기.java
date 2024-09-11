import java.util.*;

class Solution {
    
    static final String NEXT = "next";
    static final String PREV = "prev";
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        Time video = new Time(video_len);
        Time cur = new Time(pos);
        Time opStart = new Time(op_start);
        Time opEnd = new Time(op_end);
    
        cur.setVideo(video);
        cur.setOp(opStart,opEnd);
        
        
        for(String command : commands){
        
            
            if(command.equals(NEXT)){
                cur.next();
            }else if(command.equals(PREV)){
                cur.prev();
            }
        
            if(cur.checkOp()){
                cur.skip();
            }
        }
        
        String answer = cur.toString();
        return answer;
    }
    
    public static class Time{
        
        Time video;
        Time opStart;
        Time opEnd;
        int m;
        int s;
        
        public Time(String input){
            String[] temp = input.split(":");
            this.m = Integer.parseInt(temp[0]);
            this.s = Integer.parseInt(temp[1]);
        }
        
        public void setVideo(Time video){
            this.video = video;
        }
        
        public void setOp(Time start,Time end){
            this.opStart = start;
            this.opEnd = end;
            
            if(checkOp()){
                skip();
            }
        }
        
        public void skip(){
            this.m = opEnd.m;
            this.s = opEnd.s;
        }
        
        public void next(){
            this.s += 10;
            if(s >= 60){
                this.m ++;
                this.s -= 60;
            }
            
            if(this.video.m <= this.m){
                
                if(this.video.s < this.s){
                    this.s = this.video.s;
                }
            }
        }
        public void prev(){
            this.s -= 10;
            if(s < 0){
                this.m --;
                this.s += 60;
            }
            
            if(this.m < 0){
                this.m = 0;
                this.s = 0;
            }
        }
        
        public String toString(){
            String mStr = Integer.toString(this.m);
            if(mStr.length() == 1){
                mStr = "0"+mStr;
            }
            String sStr = Integer.toString(this.s);
            if(sStr.length() == 1){
                sStr = "0"+sStr;
            }
            return mStr+":"+sStr;
        }
        
        public boolean checkOp(){
            return isSame(this.opStart) || isSame(this.opEnd) || (isPrev(this.opEnd) && isNext(this.opStart));
        }
        
        public boolean isPrev(Time t){
            return this.m < t.m || (this.m == t.m && this.s < t.s); 
        }
        
        public boolean isNext(Time t){
            return this.m > t.m || (this.m == t.m && this.s > t.s); 
        }
        
        public boolean isSame(Time t){
            return this.m == t.m && this.s == t.s; 
        }
    }
}