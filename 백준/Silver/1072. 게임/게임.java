
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        long totalGame = input[0];
        long winGame = input[1];

        long winRate = (long) Math.floor((double) winGame * 100 / totalGame);

        long start = 1;
        long end = (long) 1e9 + 1;
        long result = -1L;
        while (start <= end) {

            long mid = (start + end) / 2;

            long nextWinrate = (long) Math.floor((double) (mid + winGame) * 100 / (totalGame + mid));

            if (nextWinrate > winRate) {
                end = mid-1;
                result = mid;
            } else {
                start = mid+1;
            }
        }
        System.out.println(result);
    }
}

/**
 * 5
 * -98 -97 1 2 92
 * 1 2
 */