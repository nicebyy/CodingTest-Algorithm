//import java.util.*;
//import java.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {0,0,1,-1},dy={1,-1,0,0};
    static int size;
    static int answer = -1;
    static int[][] map;
    static int[][] maxDepth;
    public static void main(String[] args) throws Exception {
        size = Integer.parseInt(br.readLine());
        map = new int[size+1][size+1];
        maxDepth = new int[size+1][size+1];
        

        for(int i=1;i<=size;i++){
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            System.arraycopy(row, 0, map[i], 1, size);
            Arrays.fill(maxDepth[i],1);
        }
        
        for(int i=1;i<=size;i++){
            for(int j=1;j<=size;j++){
                dfs(i,j);
            }
        }
        System.out.println(answer);
    }


    public static int dfs(int x,int y){

        if(maxDepth[y][x] != 1)
            return maxDepth[y][x];

        for(int i=0;i<dx.length;i++){
            int nextX = dx[i]+x;
            int nextY = dy[i]+y;

            if(isRange(nextX,nextY) && map[nextY][nextX] > map[y][x]){
                maxDepth[y][x] = Math.max(maxDepth[y][x],dfs(nextX,nextY)+1);
            }
        }
        answer = Math.max(answer,maxDepth[y][x]);
        return maxDepth[y][x];
    }

    public static boolean isRange(int x,int y){
        return x>0&&y>0&&x<=size&&y<=size;
    }
}
