
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
        int target = input[0];
        int m = input[1];

        int[] arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        Arrays.sort(arr);

        int start = 1;
        int end = arr[arr.length-1]+1;
        int sum = 0;
        int result = 0;
        while (start < end){

            int mid = (start + end)/2;
            sum = stream(arr).filter(value -> value >= mid).map(value -> value / mid).sum();

            if(sum >= target){
                start = mid+1;
                if(result < mid){
                    result = mid;
                }
            }else{
                end = mid;
            }
        }

        System.out.println(result);
    }
}