import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int w,h;
    static int[] dx = {0,0,1,-1},dy={1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-->0){

            int[] input = Arrays.stream(br.readLine().split(" "))
                     .mapToInt(Integer::parseInt).toArray();
            h = input[0];
            w = input[1];
            int[][] map = new int[h][w];

            LinkedList<int[]> q = new LinkedList<>();

            for(int i=0;i<input[2];i++){
                int[] point = Arrays.stream(br.readLine().split(" "))
                         .mapToInt(Integer::parseInt).toArray();
                map[point[0]][point[1]] = 1;
                q.add(new int[]{point[1],point[0]});
            }


            int num = 1;
            while (!q.isEmpty()){

                int[] cur = q.poll();

                if(map[cur[1]][cur[0]] == 1){
                    num++;
                    map[cur[1]][cur[0]] = num;
                    dfs(cur,map,num);
                }
            }
            System.out.println(num-1);
        }

    }

    private static void dfs(int[] cur,int[][] map,int num) {

        for(int i=0;i<dx.length;i++){
            int nextX = cur[0]+dx[i];
            int nextY = cur[1]+dy[i];

            if(isRange(nextX,nextY) && map[nextY][nextX] == 1){
                map[nextY][nextX] = num;
                dfs(new int[]{nextX,nextY},map,num);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<w && y<h;
    }
}
