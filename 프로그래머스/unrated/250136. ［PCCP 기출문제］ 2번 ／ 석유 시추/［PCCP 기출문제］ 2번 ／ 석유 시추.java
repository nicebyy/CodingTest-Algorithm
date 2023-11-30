
import java.util.*;


class Solution {

    public int[] start;
    public int h,w;
    public int curDepth = 0;
    public int solution(int[][] land) {

        h = land.length;
        w = land[0].length;

        start = new int[w];
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        for(int i=0;i<w;i++){

            for(int j=0;j<h;j++){

                if(land[j][i] > 0){
                    HashSet<Integer> xList = new HashSet<>();
                    LinkedList<int[]> queue = new LinkedList<>();
                    curDepth = 0;
                    queue.add(new int[]{i,j});

                    while(!queue.isEmpty()){

                        int[] cur = queue.poll();
                        land[cur[1]][cur[0]] = -1;
                        xList.add(cur[0]);
                        curDepth++;

                        for(int dir=0;dir<4;dir++){

                            int nextX = dx[dir] + cur[0];
                            int nextY = dy[dir] + cur[1];

                            if(isRange(nextX,nextY) && land[nextY][nextX] > 0 ){
                                land[nextY][nextX] = -1;
                                queue.add(new int[]{nextX,nextY});
                            }
                        }
                    }

                    for(Integer xPoint : xList){
                        start[xPoint] += curDepth;
                    }
                }
            }
        }

        return Arrays.stream(start).max().orElse(0);
    }
    public boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w && y<h;
    }
}
