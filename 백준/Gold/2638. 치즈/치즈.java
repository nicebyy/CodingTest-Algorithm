
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.stream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int h, w;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        h = input[0];
        w = input[1];
        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            map[i] = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        LinkedList<int[]> queue = new LinkedList<>();
        int time = 0;
        boolean isDone = false;

        do {
            visit = new boolean[h][w];
            markAir(0, 0);

            isDone = true;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (checkCheese(j, i)) { // 다음 녹을 치즈라면
                        queue.add(new int[]{j, i});
                    }
                }
            }

            if (!queue.isEmpty()) {
                time++;
                isDone = false;
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    int x = poll[0];
                    int y = poll[1];
                    map[y][x] = 0;
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == -1) {
                        map[i][j] = 0;
                    }
                }
            }

        } while (!isDone);

        System.out.println(time);

    }

    private static boolean checkCheese(int x, int y) {

        if(map[y][x] != 1){
            return false;
        }

        int cnt = 0;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isRange(nextX, nextY) && map[nextY][nextX] == -1) {
                cnt++;
            }
        }
        return cnt > 1;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < w && y < h;
    }

    public static void printMap() {
        for (int i = 0; i < h; i++) {
            String result= Arrays.toString(map[i]);
            result = result.replaceAll("-1","-");
            System.out.println(result);
        }
        System.out.println();
    }

    public static void markAir(int x, int y) {

        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!isRange(nextX, nextY) || visit[nextY][nextX]) {
                continue;
            }

            if (map[nextY][nextX] == 0) {
                map[nextY][nextX] = -1;
                visit[nextY][nextX] = true;
                markAir(nextX, nextY);
            }
        }
    }
}

/**
 * 7 7
 * 0 0 0 0 0 0 0
 * 0 0 0 1 0 0 0
 * 0 0 1 0 1 0 0
 * 0 1 0 1 0 1 0
 * 0 0 1 0 1 0 0
 * 0 0 0 1 0 0 0
 * 0 0 0 0 0 0 0
 */