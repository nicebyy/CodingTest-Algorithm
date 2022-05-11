import java.util.*;

import static java.util.Arrays.stream;


public class Main {
    
    static Fish[] fish = new Fish[17];
    static int[][] map = new int[4][4];
    static int max = 0;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int fishNum = sc.nextInt();
                int fishDir = sc.nextInt();
                map[i][j] = fishNum;
                fish[fishNum] = new Fish(i, j, fishDir, true);
            }
        }
        //첫번째 위치 먹고 start
        int fishNum = map[0][0];
        fish[fishNum].alive = false;
        map[0][0] = -1;
        dfs(0, 0, fish[fishNum].dir, fishNum);
        System.out.println(max);
    }

    //상어 위치 (x, y)
    public static void moveFish(int x, int y) {
        for (int i = 1; i <= 16; i++) {
            Fish moveFish = fish[i];
            
            //이미 먹은 물고기인 경우
            if (!moveFish.alive) 
                continue;

            //8방향 이동여부 확인
            for (int j = 0; j < 8; j++) {
                int nextDir = (moveFish.dir + j) >= 9 ? (moveFish.dir + j) % 8 : (moveFish.dir + j);
                int nextX = moveFish.x + dx[nextDir];
                int nextY = moveFish.y + dy[nextDir];

                if (!isRange(nextX,nextY)|| (nextX == x && nextY == y))
                    continue;

                if (map[nextX][nextY] != -1) {
                    Fish otherFish = fish[map[nextX][nextY]];
                    map[moveFish.x][moveFish.y] = map[nextX][nextY];
                    map[nextX][nextY] = i;
                    otherFish.x = moveFish.x;
                    otherFish.y = moveFish.y;
                    moveFish.x = nextX;
                    moveFish.y = nextY;
                    moveFish.dir = nextDir;

                } else {
                    map[moveFish.x][moveFish.y] = -1;
                    map[nextX][nextY] = i;
                    moveFish.x = nextX;
                    moveFish.y = nextY;
                    moveFish.dir = nextDir;
                }
                break;
            }

        }
    }

    public static void dfs(int x, int y, int d, int sum) {
        //결과값 갱신
        if (max < sum) max = sum;

        //fish, map 상태 저장해둠
        Fish[] cloneFish = new Fish[17];
        for (int i = 1; i < 17; i++)
            cloneFish[i] = new Fish(fish[i].x, fish[i].y, fish[i].dir, fish[i].alive);
        int[][] cloneMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            cloneMap[i] = map[i].clone();
        }
        moveFish(x, y); // 물고기 이동,회전

        for (int i = 1; i < 4; i++) { // 상어 이동
            int nextX = x + dx[d] * i;
            int nextY = y + dy[d] * i;

            if (!isRange(nextX,nextY))
                continue;
            //살아있는 물고기가 있는 경우
            int fishNum = map[nextX][nextY];
            if (fishNum != -1) {
                fish[fishNum].alive = false;
                map[nextX][nextY] = -1;
                dfs(nextX, nextY, fish[fishNum].dir, sum + fishNum);
                fish[fishNum].alive = true;
                map[nextX][nextY] = fishNum;
            }
        }

        //fish, map 상태 복구
        fish = cloneFish.clone();
        for (int i = 0; i < 4; i++)
            map[i] = cloneMap[i].clone();
    }

    static boolean isRange(int x,int y){
        return x>=0&&y>=0&&x<4&&y<4;
    }

    public static class Fish {
        int x, y, dir;
        boolean alive;

        public Fish(int x, int y, int dir, boolean alive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = alive;
        }
    }
}
