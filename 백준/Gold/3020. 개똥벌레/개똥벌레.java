
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, h;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = input[0];
        h = input[1];
        down = new int[h + 2];
        up = new int[h + 2];

        for (int i = 1; i <= n; i++) {

            int e = Integer.parseInt(br.readLine());

            if (i % 2 == 1) down[e]++;
            else up[h - e + 1]++;
        }

        for (int i = 1; i <= h; i++) {
            down[i] += down[i - 1];
        }


        for (int i = h; i >= 1; i--) {
            up[i] += up[i + 1];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= h; i++) {
            int sum = down[h] - down[i - 1] + up[1] - up[i + 1];

            if (sum < min) {
                cnt = 1;
                min = sum;
            } else if (sum == min) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}
