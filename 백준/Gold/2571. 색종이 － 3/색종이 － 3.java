
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    static final int SIZE = 101;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[SIZE][SIZE];

        for (int i = 0; i < n; i++) {
            int[] input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            fillRectangle(input[0], input[1], map);
        }
        
        for (int i = 1; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (map[i][j] != 0 && map[i + 1][j] != 0) {
                    map[i + 1][j] = map[i][j] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {

                int h = 100;
                for (int k = j; k < SIZE; k++) {

                    h = Math.min(map[i][k], h);
                    if (h == 0) break;
                    ans = Math.max(ans, h * (k - j + 1));
                }
            }
        }
        System.out.println(ans);
    }

    static void fillRectangle(int x, int y, int[][] map) {

        for (int i = y; i < y + 10; i++) {
            for (int j = x; j < x + 10; j++) {
                map[i][j] = 1;
            }
        }
    }
}








