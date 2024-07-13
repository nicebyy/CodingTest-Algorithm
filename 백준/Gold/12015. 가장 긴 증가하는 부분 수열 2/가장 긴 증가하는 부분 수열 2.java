
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] lis = new int[n];

        int index = 1;
        lis[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int last = lis[index - 1];

            if (arr[i] > last) {
                index++;
                lis[index - 1] = arr[i];
            } else {

                int start = 0;
                int end = index;

                while (start < end) {

                    int mid = (start + end) / 2;

                    if (lis[mid] < arr[i]) {
                        start = mid+1;
                    } else {
                        end = mid;
                    }
                }
                lis[start] = arr[i];
            }
        }

        System.out.println(index);
    }

}
/**
 * 4 7 10 3 1 8 7 2 5 7
 * <p>
 * 4
 * 4 7
 * 4 7 10
 * 3 7 10
 * 1 7 10
 * 2 7 8
 */

