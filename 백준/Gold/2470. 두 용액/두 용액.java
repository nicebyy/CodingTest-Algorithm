
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static int MAX_COMBINE_ABS = 2000000001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        int[] potion = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(potion);
        int start = 0;
        int end = n - 1;
        int minCombine = MAX_COMBINE_ABS;

        int minStart = 0;
        int minEnd = 0;
        while (start < end) {

            int combine = potion[start] + potion[end];
            int combineAbs = Math.abs(potion[start] + potion[end]);

            if (minCombine > combineAbs) {
                minCombine = combineAbs;
                minStart = start;
                minEnd = end;
            }

            if (combine > 0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(potion[minStart] + " " + potion[minEnd]);

    }
}

/**
 * 5
 * -98 -97 1 2 92
 * 1 2
 */