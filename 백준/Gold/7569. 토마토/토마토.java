import java.lang.reflect.Array;
import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

public class Main {


    static int[] dx={0,0,1,-1},dy={1,-1,0,0},dz={1,-1};
    static int w,h,z;
    static int[][][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int[] size = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        w = size[0];
        h = size[1];
        z = size[2];
        map = new int[z][h][w];

        LinkedList<Point> q = new LinkedList<>();

        for(int i=0;i<z;i++){

            for(int j=0;j<h;j++){
                map[i][j] = stream(br.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                for(int k=0;k<w;k++){
                    if(map[i][j][k]==1)
                        q.add(new Point(k,j,i,0));
                }
            }
        }

        int time=0;

        while (!q.isEmpty()){

            Point cur = q.poll();

            time = cur.time;

            for(int i=0;i<dx.length;i++){

                int nextX = dx[i]+cur.x;
                int nextY = dy[i]+cur.y;

                if(isRange(nextX,nextY,cur.z) && map[cur.z][nextY][nextX]==0){
                    q.add(new Point(nextX,nextY,cur.z,cur.time+1));
                    map[cur.z][nextY][nextX]=1;
                }
            }

            for(int i=0;i<dz.length;i++){

                int nextZ = dz[i]+cur.z;

                if(isRange(cur.x,cur.y,nextZ) && map[nextZ][cur.y][cur.x]==0){
                    q.add(new Point(cur.x,cur.y,nextZ,cur.time+1));
                    map[nextZ][cur.y][cur.x]=1;
                }
            }
        }

        for(int i=0;i<z;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<w;k++){
                    if(map[i][j][k]==0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(time);
    }
    static boolean isRange(int x,int y,int zz){

        return x>=0&&y>=0&&x<w&&y<h&&zz>=0&&zz<z;
    }


    private static class Point {
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", time=" + time +
                    '}';
        }

        int x,y,z;
        int time;

        public Point(int x, int y, int z,int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
