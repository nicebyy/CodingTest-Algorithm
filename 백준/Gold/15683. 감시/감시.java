
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int h,w,answer;
    static int[][] map;
    static HashSet<List<Integer>>[] dirSetArr;
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = input[0];
        w = input[1];
        map = new int[h][w];
        answer = h*w;
        for(int i=0;i<h;i++){
            map[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            for(int j=0;j<w;j++){
                if(map[i][j]>=1 && map[i][j]<=5){
                    cctvList.add(new CCTV(j,i,map[i][j]));
                }
            }
        }

        setDirSet();

        dfs(0,map);
        System.out.println(answer);
    }

    public static void dfs(int depth,int[][] curMap){

        if(depth == cctvList.size()){
            int count = stream(curMap).mapToInt(arr -> (int) stream(arr).filter(e -> e == 0).count()).sum();
            answer = Math.min(count,answer);
            return;
        }
        CCTV cctv = cctvList.get(depth);

        for (List<Integer> list : dirSetArr[cctv.type]) {

            int[][] mapClone = new int[h][w];

            for(int col=0;col<map.length;col++){
                mapClone[col] = curMap[col].clone();
            }

            int[][] nextMap = cctv.applyCCTV(mapClone, list);
            dfs(depth+1,nextMap);
        }
    }

    private static void setDirSet() {

        dirSetArr = new HashSet[6];
        dirSetArr[1] = new HashSet<>(Set.of(List.of(0), List.of(1), List.of(2), List.of(3)));
        dirSetArr[2] = new HashSet<>(Set.of(List.of(0, 1), List.of(2, 3)));
        dirSetArr[3] = new HashSet<>(Set.of(List.of(0, 3), List.of(1, 3), List.of(1, 2), List.of(0, 2)));
        dirSetArr[4] = new HashSet<>(Set.of(List.of(0, 1, 2), List.of(1, 2, 3), List.of(2, 3, 0), List.of(3, 0, 1)));
        dirSetArr[5] = new HashSet<>(Set.of(List.of(0, 1, 2, 3)));

    }

    public static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w && y<h;
    }

    public static void printMap(int[][] map){
        System.out.println("===================================");
        for(int i=0;i<h;i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    static class CCTV{
         int x;
         int y;
         int type;

        public CCTV(int x, int y,int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int[][] applyCCTV(int[][] map, List<Integer> directions){ // directions : 한번에 탐색할 방향들

            // dir 0 1 2 3 = up down left right
            int[] dx = {0,0,-1,1};
            int[] dy = {-1,1,0,0};

            for (Integer nextDir : directions) { // 주어진 방향으로 모두 탐색

                int nextX = this.x;
                int nextY = this.y;

                while (true){ // cctv가 있거나 이미 마스킹 된 자리면 다시탐색

                    nextX += dx[nextDir];
                    nextY += dy[nextDir];

                    if(!isRange(nextX,nextY) || map[nextY][nextX] == 6){ // 벽을 만나면 탐색 종료
                        break;
                    } else if(map[nextY][nextX] == 0){ // 빈공간
                        map[nextY][nextX] = -1;
                    }
                }
            }
            return map;
        }
    }
}


