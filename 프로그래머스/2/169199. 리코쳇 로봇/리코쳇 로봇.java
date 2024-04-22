import java.utils.*;

class Solution {
    
    public int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    public int w,h;
    public int solution(String[] board) {
        
        int[] start;
        int[] end;
        h = board.length;
        w = board[0].length;
        
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                
                if(board[i][j].equals("R")){
                    start = new int[]{j,i,0};
                }else if(board[i][j].equals("G")){
                    end = new int[]{j,i};
                }
            }
        }
        
        List<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{})
        
        while(!q.isEmpty()){
            
            int[] cur = q.poll();
            
            if(cur[0] == end[0] && cur[1] == end[1]){
                return cur[2];
            }
            
            for(int dir=0;dir<dx.length;dir++){
                
                int nextX = cur[0];
                int nextY = cur[1];
                
                while(isRange(nextX+dx[dir],nextY+dy[dir]) && 
                      !board[nextY+dy[dir]][nextX+dx[dir]].equals("D")){
                    
                    nextX += dx[dir];
                    nextY += dy[dir];
                }
                
                if(cur[0] != nextX || cur[1] != nextY){
                    
                    q.add(new int[]{nextX,nextY,cur[2]+1});
                }
            }
            
        }
    
        return -1;
    }
    
    public boolean isRange(int x,int y){
        
        return y>=0&&x>=0&&x<w&&y<h;
    }
}