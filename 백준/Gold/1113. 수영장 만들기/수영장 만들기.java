
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int h;
    public static int w;
    public static int[][] map;
    public static int[][] diff;
    public static boolean[][] visit;
    public static int maxHeight = 1;
    public static int minHeight = 9;
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        h = input[0];
        w = input[1];

        map = new int[h+2][w+2];
        diff = new int[h+2][w+2];
        visit = new boolean[h+2][w+2];

        for(int i=1; i<=h; i++){
            input = stream(("0"+br.readLine()+"0").split(""))
                     .mapToInt(Integer::parseInt).toArray();
            map[i] = input;
            for(int j=1; j<=w; j++){
                if(map[i][j] > maxHeight){
                    maxHeight = map[i][j];
                }
                if(map[i][j] < minHeight){
                    minHeight = map[i][j];
                }
            }
        }

        for(int i=1;i<h+2; i++){
            for(int j=1;j<w+2;j++){
                if(map[i][j] > 0){
                    diff[i][j] = maxHeight - map[i][j];
                }
            }
        }

        int round = maxHeight - minHeight;

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        while (round -- > 0){

            ArrayDeque<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{0,0});
            visit = new boolean[h+2][w+2];
            visit[0][0] = true;

            while (!queue.isEmpty()){

                int[] poll = queue.poll();
                int curX = poll[0];
                int curY = poll[1];

                for(int i=0; i<dx.length; i++){
                    int nextX = curX + dx[i];
                    int nextY = curY + dy[i];

                    if(!isRange(nextX, nextY) || visit[nextY][nextX] || isWall(nextX,nextY)){
                        continue;
                    }

                    if(!isPadding(nextX, nextY)){
                        diff[nextY][nextX]--;
                    }
                    visit[nextY][nextX]=true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        int waterSum = stream(diff).mapToInt(row -> stream(row).sum()).sum();
        System.out.println(waterSum);
    }
    public static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w+2 && y<h+2;
    }

    public static boolean isWall(int x, int y){
        return diff[y][x] == 0 && map[y][x] > 0;
    }

    public static boolean isPadding(int x, int y){
        return diff[y][x] == 0 && map[y][x] == 0;
    }
}






