import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int w,h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        h = input[1];
        w = input[0];

        int[][] map = new int[h][w];
        LinkedList<int[]> q = new LinkedList<>();

        for(int i=0;i<h;i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            for(int j=0;j<w;j++){
                if(map[i][j]==1) {
                    q.add(new int[]{j, i, 0});
                }
            }
        }

        int[] dx = {0,0,1,-1}, dy={1,-1,0,0};
        int days = 0;
        while (!q.isEmpty()){

            int[] cur = q.poll();
            days = Math.max(days,cur[2]);

            for(int i=0;i<dx.length;i++){
                int nextX = dx[i] + cur[0];
                int nextY = dy[i] + cur[1];

                if(isRange(nextX,nextY) && map[nextY][nextX] == 0){
                    map[nextY][nextX]=1;
                    q.add(new int[]{nextX,nextY,cur[2]+1});
                }
            }
        }

        for (int[] e : map) {
            if(days == -1) break;

            for (int i : e) {
                if(i == 0){
                    days = -1;
                    break;
                }
            }
        }
        System.out.println(days);
    }

    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<w && y<h;
    }
}
