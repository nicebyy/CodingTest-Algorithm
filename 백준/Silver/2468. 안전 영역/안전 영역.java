
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static int n;
    static int[][] map;
    static boolean[][] visit;
    static int max = 1;
    static TreeSet<Integer> processSet = new TreeSet<Integer>();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            map[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            for(int j=0;j<n;j++){
                processSet.add(map[i][j]);
            }
        }

        for (Integer next : processSet) {
            int landCount = 0;
            visit = new boolean[n][n];
            for(int y=0;y<n;y++){
                for(int x=0;x<n;x++){

                    if(map[y][x] > next && !visit[y][x]){
                        landCount ++;
                        dfs(x,y,next);
                    }
                }
            }
            max = Math.max(max,landCount);
        }

        System.out.println(max);
    }

    private static void dfs(int x, int y,int next) {

        visit[y][x] = true;

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        for(int i=0;i<dx.length;i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(isRange(nextX,nextY) && !visit[nextY][nextX] && map[nextY][nextX] > next){
                dfs(nextX,nextY,next);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<n && y<n;
    }
}
