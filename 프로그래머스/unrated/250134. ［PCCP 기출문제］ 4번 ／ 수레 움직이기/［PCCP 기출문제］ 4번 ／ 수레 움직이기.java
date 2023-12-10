
import java.io.*;

class Solution {

    //    public boolean[][][][] visit;
    public boolean[][] redVisit;
    public boolean[][] blueVisit;

    public static Point redStart,blueStart,redEnd,blueEnd;
    public static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    public int minDepth = Integer.MAX_VALUE;
    public int solution(int[][] maze) {

//        visit = new boolean
//                [maze.length][maze[0].length]
//                [maze.length][maze[0].length]; // red[y][x] blue [y][x]

        redVisit = new boolean[maze.length][maze[0].length];
        blueVisit = new boolean[maze.length][maze[0].length];

        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){

                if(maze[i][j] == 1){
                    redStart = new Point(j,i);
                }else if(maze[i][j] == 2){
                    blueStart = new Point(j,i);
                }else if(maze[i][j] == 3){
                    redEnd = new Point(j,i);
                }else if(maze[i][j] == 4){
                    blueEnd = new Point(j,i);
                }
            }
        }
//        visit[redStart.y][redStart.x][blueStart.y][blueStart.x] = true;
        redVisit[redStart.y][redStart.x] = true;
        blueVisit[blueStart.y][blueStart.x] = true;

        backtracking(maze,redStart,blueStart,0);
        return minDepth == Integer.MAX_VALUE ? 0 : minDepth;
    }

    private void backtracking(int[][] maze, Point redPoint, Point bluePoint, int depth) {
//        System.out.println("===============================");
//        for(int i=0;i<maze.length;i++){
//            for(int j=0;j<maze[0].length;j++){
//                if(redPoint.isSamePoint(new Point(j,i))){
//                    System.out.print("R ");
//                }else if(bluePoint.isSamePoint(new Point(j,i))){
//                    System.out.print("B ");
//                }else if(maze[i][j] == 5){
//                    System.out.print("X ");
//                }else{
//                    System.out.print("O ");
//                }
//            }
//            System.out.println("");
//        }

        if(redPoint.isSamePoint(redEnd) && bluePoint.isSamePoint(blueEnd)){
            minDepth = Math.min(minDepth,depth);
        }

        for(int redDir = 0 ; redDir < 4; redDir ++){

            for(int blueDir = 0; blueDir < 4; blueDir ++){

                int nextRedX = redPoint.x;
                int nextRedY = redPoint.y;
                if(!redPoint.isSamePoint(redEnd)){
                    nextRedX += dx[redDir];
                    nextRedY += dy[redDir];
                }

                int nextBlueX = bluePoint.x;
                int nextBlueY = bluePoint.y;
                if(!bluePoint.isSamePoint(blueEnd)){
                    nextBlueX += dx[blueDir];
                    nextBlueY += dy[blueDir];
                }

                if(isRange(nextBlueX,nextBlueY,maze)
                        && isRange(nextRedX,nextRedY,maze)
//                        && !redVisit[nextRedY][nextRedX]
//                        && !blueVisit[nextBlueY][nextBlueX]
                        && maze[nextRedY][nextRedX] != 5
                        && maze[nextBlueY][nextBlueX] != 5
                ){

                    if(!redPoint.isSamePoint(redEnd) && redVisit[nextRedY][nextRedX]){
                        continue;
                    }else if(!bluePoint.isSamePoint(blueEnd) && blueVisit[nextBlueY][nextBlueX]){
                        continue;
                    }

                    Point nextRedPoint = new Point(nextRedX, nextRedY);
                    Point nextBluePoint = new Point(nextBlueX, nextBlueY);

                    if(
                            nextBluePoint.isSamePoint(nextRedPoint) // 서로 목적지 겹침
                                    || (nextBluePoint.isSamePoint(redPoint) && nextRedPoint.isSamePoint(bluePoint)) // 서로 위치 교차
                                    || depth + 1 >= minDepth
                    ){
                        continue;
                    }
                    redVisit[nextRedY][nextRedX] = true;
                    blueVisit[nextBlueY][nextBlueX] = true;

                    backtracking(maze,nextRedPoint,nextBluePoint,depth+1);

                    if(!redPoint.isSamePoint(nextRedPoint))
                        redVisit[nextRedY][nextRedX] = false;
                    if(!bluePoint.isSamePoint(nextBluePoint))
                        blueVisit[nextBlueY][nextBlueX] = false;
                }
            }
        }
    }

    public boolean isRange(int x,int y,int[][] maze){

        return x>=0 && y>=0 && x<maze[0].length && y< maze.length;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSamePoint(Point target) {
            return this.x == target.x && this.y == target.y;
        }
    }
}
