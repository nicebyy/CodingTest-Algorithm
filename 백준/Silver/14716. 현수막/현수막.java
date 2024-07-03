
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int h,w;
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = input[0];
        w = input[1];

        int[][] map = new int[h][w];
        ArrayList<Point> enList = new ArrayList<>();

        for(int i=0;i<h;i++){
            map[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            for(int j=0;j<w;j++){
                if(map[i][j] == 1){
                    enList.add(new Point(j,i));
                }
            }
        }

        boolean[][] visit = new boolean[h][w];

        int wordCount = 0;
        for (Point point : enList) {

            if(checkCond(point.x, point.y,visit,map)){
                wordCount++;
                find(point,visit,map);
            }
        }

        System.out.println(wordCount);
    }

    private static void find(Point cur,boolean[][] visit,int[][] map) {

        int[] dx = {1,-1,0,0,1,-1,1,-1};
        int[] dy = {0,0,1,-1,1,-1,-1,1};

        visit[cur.y][cur.x]=true;

        for(int i=0;i<dx.length;i++){
            int nextX = dx[i] + cur.x;
            int nextY = dy[i] + cur.y;

            if(isRange(nextX,nextY) && checkCond(nextX,nextY,visit,map)){
                find(new Point(nextX,nextY),visit,map);
            }
        }
    }

    private static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w && y<h;
    }

    private static boolean checkCond(int x,int y,boolean[][] visit,int[][] map){
        return !visit[y][x] && map[y][x] == 1;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

