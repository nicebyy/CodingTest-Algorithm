
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int k;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = k;

        while (start < end) {

            long mid = (start + end) / 2;
            long count = 0;
            
            for (int i = 1; i <= n; i++) {
                count += Math.min(n, mid / i);
            }

            if (k <= count) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}
