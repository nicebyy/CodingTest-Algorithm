
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int start = 0;
        int end = n-1;
        int min = Integer.MAX_VALUE;
        int from=0, to=0;
        while (start < end){

            int value = arr[start] + arr[end];
            int abs = Math.abs(value);
            if(abs <= min){
                min = abs;
                from = arr[start];
                to = arr[end];
            }
            if(value > 0){
                end --;
            }else {
                start++;
            }
        }
        System.out.println(from+" "+to);
//        System.out.println(min);
    }
}

/**
 *
 * 6
 * -99 -2 -1 4 98 200
 *
 * 5
 * -5 -4 -3 -2 -1
 */