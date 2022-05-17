import java.util.*;
import java.io.*;

public class Main{
    static int K,N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]); N = Integer.parseInt(input[1]);
        long max=0;
        arr = new long[K];
        for(int i=0;i<K;i++) {
            arr[i] = Long.parseLong(br.readLine());
            max = Math.max(arr[i],max);
        }
        long left = 1;
        long right = max;

        while(left<=right){
            long mid = (right+left)/2;
            long sum = 0;
            for(int i=0;i<K;i++){
                sum += arr[i]/mid;
            }
            if(sum >= N)
                left = mid+1;
            else
                right = mid-1;
        }
        System.out.println(right);

    }
}


