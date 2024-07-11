
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int n = input[0];
        int m = input[1];

        long[] arr = stream(br.readLine().split(" "))
                            .mapToLong(Long::parseLong)
                            .toArray();
        long heightRange = stream(arr).max().getAsLong();
        
        long start = 0, end = heightRange;

        while (start+1 < end){

            long mid = (start+end)/2;

            if(getSum(arr, mid) > m){
                start = mid;
            }else{
                end = mid;
            }
        }

        long result = start;
        if(getSum(arr,end) >= m){
            result = end;
        }

        System.out.println(result);
    }


    private static long getSum(long[] arr, long height) {

        long sum = 0;

        for (long tree : arr) {

            if(tree - height >= 0){
                sum += tree - height;
            }
        }
        return sum;
    }
}

/**
 *
 * start end mid
 * 0 3 1
 * 0 1 0
 *
 */