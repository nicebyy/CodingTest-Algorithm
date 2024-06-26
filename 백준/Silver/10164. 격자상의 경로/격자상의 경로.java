
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int h = input[0];
        int w = input[1];

        map = new int[h][w]; //{1,2}
        int[] intersection = getIntersection(input[2], h, w);
        map[0][0] = 1;

        int toIntersection = getPathCount(new int[]{0, 0}, intersection);
        map[intersection[1]][intersection[0]] = 1;
        int toEnd = getPathCount(intersection, new int[]{w - 1, h - 1});

        System.out.println(toIntersection * toEnd);
    }

    private static int getPathCount(int[] start, int[] goal) {
        for (int i = start[1]; i <= goal[1]; i++) {
            for (int j = start[0]; j <= goal[0]; j++) {

                if (i == start[1] && j == start[0]) {
                    continue;
                }

                if (i == start[1] && j > start[0]) {
                    map[i][j] += map[i][j - 1];
                } else if (i > start[1] && j == start[0]) {
                    map[i][j] += map[i - 1][j];
                } else {
                    map[i][j] += map[i][j - 1] + map[i - 1][j];
                }
            }
        }
        return map[goal[1]][goal[0]];
    }

    private static int[] getIntersection(int k, int h, int w) {

        if (k == 0) {
            return new int[]{0, 0};
        }

        int x = k % w;
        int y = 0;
        if (x > 0) {
            y = 1 + k / w;
        }
        return new int[]{x - 1, y - 1};
    }
}
