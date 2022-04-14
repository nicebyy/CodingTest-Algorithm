import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static int w, h,max=0;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        h = input[0]; w = input[1];
        map = new int[h][w];
        visit = new boolean[h][w];
        for(int i=0;i<h;i++){
             map[i] = stream(br.readLine().split(" "))
                      .mapToInt(Integer::parseInt).toArray();
//            System.out.println(Arrays.toString(map[i]));
        } // end input

        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                visit[y][x] = true;
                dfs(x,y,1,map[y][x]);
                bfs(x,y);
                visit[y][x] = false;
            }
        }

        System.out.println(max);
    }

    static int[] dx={0,0,1,-1},dy={1,-1,0,0};
    private static void dfs(int x, int y, int depth, int sum) {

        if(depth==4){
            //todo
            max = Math.max(max,sum);
            return;
        }

        for(int dir=0;dir<dx.length;dir++){
            int nextX = dx[dir]+x;
            int nextY = dy[dir]+y;

            if(!isRange(nextX,nextY) || visit[nextY][nextX])
                continue;

            visit[nextY][nextX]=true;
            dfs(nextX,nextY,depth+1,sum+map[nextY][nextX]);
            visit[nextY][nextX]=false;
        }

    }

    private static void bfs(int x, int y) {

        int sum=map[y][x];
        LinkedList<Integer> tBlocks = new LinkedList<>();

        for(int dir=0;dir<dx.length;dir++){
            int nextX = dx[dir]+x;
            int nextY = dy[dir]+y;

            if(!isRange(nextX,nextY))
                continue;

            tBlocks.add(map[nextY][nextX]);
            sum+=map[nextY][nextX];
        }
        
        if(tBlocks.size()>3){
            for (Integer t : tBlocks) 
                max = Math.max(max,sum-t);
        }else if(tBlocks.size()==3){
            max = Math.max(max,sum);
        }
        
    }

    private static boolean isRange(int x,int y){
        return x>=0&&y>=0&&x<w&&y<h;
    }
}

