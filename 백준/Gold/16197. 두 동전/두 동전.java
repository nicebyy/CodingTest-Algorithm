import java.util.*;
import java.io.*;

public class Main{
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int w,h;
    static Queue<Move> queue = new PriorityQueue<>();
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        h = Integer.parseInt(input[0]); w = Integer.parseInt(input[1]);
        map = new char[h+2][w+2];
        Point[] start = new Point[2];
        int startIndex=0;
        for(int i=1;i<=h;i++) {
            char[] chars = br.readLine().toCharArray();
            for(int j=1;j<=w;j++) {
                map[i][j] = chars[j - 1];
                if(map[i][j]=='o') {
                    start[startIndex] = new Point(j,i);
                    startIndex++;
                }
            }
        }
        queue.add(new Move(start[0],start[1],0));
        while (!queue.isEmpty()){
            Move move = queue.poll();
            Point p1 = move.p1;
            Point p2 = move.p2;
            if(move.cnt>10){
                System.out.println(-1);
                return;
            }
            for(int i=0;i<dx.length;i++){
                int nextX1 = p1.x+dx[i];
                int nextY1 = p1.y+dy[i];
                int nextX2 = p2.x+dx[i];
                int nextY2 = p2.y+dy[i];

                //둘다 떨어지면 pass
                if(!isRange(nextX1,nextY1)&&!isRange(nextX2,nextY2))
                    continue;
                //벽을 만날땐 그대로
                if(map[nextY1][nextX1]=='#'){
                    nextX1 = p1.x;
                    nextY1 = p1.y;
                }
                if(map[nextY2][nextX2]=='#'){
                    nextX2 = p2.x;
                    nextY2 = p2.y;
                }
                // 동전이 둗중 하나만 범위를 벗어나서 떨어지면
                if(!isRange(nextX1,nextY1)&&isRange(nextX2,nextY2) || !isRange(nextX2,nextY2)&&isRange(nextX1,nextY1)){
                    if(move.cnt+1 >10) // 10번이상 조작했다면?
                        System.out.println(-1);
                    else
                        System.out.println(move.cnt+1);
                    return;
                }
                Point nextP1 = new Point(nextX1,nextY1);
                Point nextP2 = new Point(nextX2,nextY2);
                queue.add(new Move(nextP1,nextP2, move.cnt+1));
            }
        }
    }
    static boolean isRange(int x,int y){
        return x>0&&y>0&&x<=w&&y<=h;
    }
}
class Move implements  Comparable<Move>{
    Point p1;
    Point p2;
    int cnt;
    public Move(Point p1, Point p2, int cnt) {
        this.p1 = p1;
        this.p2 = p2;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Move o) {
        return this.cnt-o.cnt;
    }
}
class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
