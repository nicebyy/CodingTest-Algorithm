import java.lang.reflect.Array;
import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

public class Main {


    static int max = 0;
    static int h,w;
    static int[][] map,copyMap;
    static boolean[][] visit;
    static int[] dx={0,0,1,-1},dy={1,-1,0,0};

    static LinkedList<Point> virusQ = new LinkedList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int[] mapSize = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = mapSize[0];
        w = mapSize[1];
        map = new int[h][w];
        copyMap = new int[h][w];

        for(int i=0;i<h;i++){
            map[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            for(int j=0;j<w;j++)
                if(map[i][j]==2)
                    virusQ.add(new Point(j,i));
        }

        makeWall(0);
        System.out.println(max);
    }

    private static void makeWall(int depth) {

        if(depth==3){
            //todo
            max = Math.max(getSafeArea(),max);
            return;
        }

        for(int i=0;i<h;i++){

            for(int j=0;j<w;j++){

                if(map[i][j]==0){
                    map[i][j]=1;
                    makeWall(depth+1);
                    map[i][j]=0;
                }
            }
        }
    }

    private static int getSafeArea() {

        LinkedList<Point> q = new LinkedList<>(virusQ);

        for(int i=0;i<h;i++)
            copyMap[i] = map[i].clone();    


        while (!q.isEmpty()){

            Point poll = q.poll();

            for(int i=0;i<dx.length;i++){

                int nextX = dx[i] + poll.x;
                int nextY = dy[i] + poll.y;

                if(!isRange(nextX,nextY) || copyMap[nextY][nextX]!=0)
                    continue;

                copyMap[nextY][nextX] = 2;
                q.add(new Point(nextX,nextY));
            }
        }

        int safeArea = 0;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(copyMap[i][j]==0)
                    safeArea++;
            }
        }
        return safeArea;
    }


    static boolean isRange(int x,int y){

        return x>=0&&y>=0&&x<w&&y<h;
    }


    private static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
