
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n,target;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        Arrays.sort(arr);

        int result = 0;
        for(int start=0,end=arr.length-1;start<end;){

            if(arr[start] + arr[end] == target){
                start++;
                end--;
                result++;
            }else if(arr[start] + arr[end] > target){
                end--;
            }else{
                start++;
            }
        }
        System.out.println(result);

    }
}


