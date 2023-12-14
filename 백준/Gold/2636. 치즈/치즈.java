
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static int h,w;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        h = input[0];
        w = input[1];
        map = new int[h][w];
        for(int y=0; y<h; y++){
            map[y] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }

        int preCount = 0;
        int turnCount = 0;
        
        while (true){

            LinkedList<Point> searchQueue = new LinkedList<>();
            HashSet<Point> processSet = new HashSet<>();

            visit = new boolean[h][w];

            searchQueue.add(new Point(0,0));
            visit[0][0] = true;

            while (!searchQueue.isEmpty()){

                Point cur = searchQueue.poll();

                for(int i=0;i<dx.length;i++){

                    int nextX = dx[i] + cur.x;
                    int nextY = dy[i] + cur.y;

                    if(!isRange(nextX,nextY)){
                        continue;
                    }

                    if(map[nextY][nextX] == 1){
                        processSet.add(new Point(nextX,nextY));
                    }else if(!visit[nextY][nextX] && map[nextY][nextX] == 0){
                        visit[nextY][nextX] = true;
                        searchQueue.add(new Point(nextX,nextY));
                    }
                }

            }

            if(processSet.isEmpty()){
                System.out.println(turnCount);
                System.out.println(preCount);
                return;
            }else{
                preCount = processSet.size();
                turnCount ++ ;
                processSet.forEach(p->map[p.y][p.x]=0);
            }
        }
    }
    private static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<w && y<h;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
