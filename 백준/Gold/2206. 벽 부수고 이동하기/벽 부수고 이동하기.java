import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][][] visit;
    static int w,h;
    static int[] dx = {0,0,1,-1},dy={1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        h = input[0];
        w = input[1];
        map = new int[h][w];
        visit = new boolean[h][w][2];

        for(int i=0;i<h;i++){
            map[i] = Arrays.stream(br.readLine().split(""))
                     .mapToInt(Integer::parseInt).toArray();
        }

        LinkedList<Point> q = new LinkedList<>();
        q.add(new Point(0,0,1,0));
        Arrays.fill(visit[0][0],true);

        while (!q.isEmpty()){

            Point cur = q.poll();
            
            if(cur.x == w-1 && cur.y == h-1){
                System.out.println(cur.depth);
                return;
            }

            for(int i=0;i<dx.length;i++){

                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if(isRange(nextX,nextY)){

                    if(map[nextY][nextX] == 0 && !visit[nextY][nextX][cur.destroy]){
                        visit[nextY][nextX][cur.destroy] = true;
                        q.add(new Point(nextX, nextY, cur.depth + 1, cur.destroy));
                        
                    }else if(map[nextY][nextX] == 1 && cur.destroy==0 && !visit[nextY][nextX][1]){
                        visit[nextY][nextX][1] = true;
                        q.add(new Point(nextX,nextY,cur.depth+1,1));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<w && y<h;
    }

    static class Point{
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", destroy=" + destroy +
                    '}';
        }

        int x;
        int y;
        int depth;
        int destroy;

        public Point(int x, int y, int depth,int destroy) {
            this.x = x;
            this.y = y;
            this.destroy = destroy;
            this.depth = depth;
        }
    }
}
