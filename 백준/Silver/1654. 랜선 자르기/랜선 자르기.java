
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input[0];
        int target = input[1];

        long[] arr = new long[n];

        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        long left = 1;
        long right = stream(arr).max().getAsLong()+1;

        while (left < right){

            long mid = (left + right) / 2;

            long sum = 0;
            for (long line : arr) {
                sum += line / mid;
            }

            if(target <= sum){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        System.out.println(right-1);
    }
}

/**
 * 5 5
 * 6
 * 6
 * 3
 * 3
 * 3
 *
 * 5 5
 * 500
 * 500
 * 500
 * 500
 * 50005
 * (10001)
 *
 * 5 5
 * 2147483647
 * 2147483647
 * 2147483647
 * 2147483647
 * 2147483647
 * (2147483647)
 */