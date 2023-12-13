
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static int h,w;
    static int[][] map;
    static boolean[][] visit;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = input[0];
        w = input[1];
        map = new int[h][w];
        List<int[]> lands = new ArrayList<>();

        for(int i=0;i<h;i++){ // W : 0  , L : 1
            map[i] = stream(br.readLine().split(""))
                    .mapToInt(s-> s.equals("W") ? 0 : 1).toArray();
            for(int j=0;j<w;j++){
                if(map[i][j] == 1)
                    lands.add(new int[]{j,i});
            }
        }

        int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
        int max = Integer.MIN_VALUE;
        for (int[] land : lands) {

            visit = new boolean[h][w];

            LinkedList<int[]> queue = new LinkedList<>();
            queue.add(new int[]{land[0],land[1],0});
            visit[land[1]][land[0]] = true;

            while (!queue.isEmpty()){

                int[] cur = queue.poll();
//                visit[cur[1]][cur[0]] = true;

                if(cur[2] > max){
                    max = cur[2];
                }

                for(int i=0;i<dx.length;i++){

                    int nextX = cur[0] + dx[i];
                    int nextY = cur[1] + dy[i];

                    if(!isRange(nextX,nextY) || visit[nextY][nextX] || map[nextY][nextX] == 0)
                        continue;
                    visit[nextY][nextX] = true;
                    queue.add(new int[]{nextX,nextY,cur[2]+1});
                }

            }

        }

        System.out.println(max);
    }
    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<w && y<h;
    }
}
