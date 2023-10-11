
import java.util.*;
import java.util.Collections.*;
import java.util.stream.Collectors;

class Solution {

    public int[] dx = {0,0,1,-1}, dy={1,-1,0,0};
    public boolean[][] visit;
    public int solution(int[][] maps) {

        visit = new boolean[maps.length][maps[0].length];
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        visit[0][0] = true;

        while (!queue.isEmpty()){

            int[] cur = queue.poll();

            if(cur[1] == maps.length-1 && cur[0] == maps[0].length-1){
                return cur[2];
            }

            for(int i=0;i<dx.length;i++){

                int nextX = dx[i] + cur[0];
                int nextY = dy[i] + cur[1];

                if(isRange(nextX,nextY) && !visit[nextY][nextX] && maps[nextY][nextX] == 1){
                    queue.add(new int[]{nextX,nextY,cur[2]+1});
                    visit[nextY][nextX]=true;
                }
            }
        }
        return -1;
    }

    public boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<visit[0].length && y<visit.length;
    }
}