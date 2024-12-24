
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int range = input[1];
        int[] arr;
        int[] sum = new int[n];
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        sum[0] = arr[0];
        for(int i=1; i<n; i++){
            sum[i] += sum[i-1]+arr[i];
        }

        int max = 0;
        int count = 0;

        for(int index = 0; index+range-1<n; index++){
            int acc = sum[index+range-1] - sum[index] + arr[index];
            if(acc > max){
                max = acc;
                count = 1;
            }else if(acc == max){
                count++;
            }
        }
        String result = max == 0 ? "SAD" : max+"\n"+count;
        System.out.println(result);
    }
}






