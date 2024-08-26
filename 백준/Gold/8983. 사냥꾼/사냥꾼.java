
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int m = input[0];
        int n = input[1];
        int range = input[2];

        int[] pos = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(pos);

        Point[] targets = new Point[n];

        int result = 0;
        for (int i = 0; i < n; i++) {
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            targets[i] = new Point(input[0], input[1]);


            int start = 0;
            int end = pos.length - 1;

            while (start <= end) {

                int mid = (start + end) / 2;

                int diff = Math.abs(targets[i].x - pos[mid]) + targets[i].y;

                if (diff <= range) {
                    result++;
                    break;
                }

                if (pos[mid] > targets[i].x) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        System.out.println(result);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
