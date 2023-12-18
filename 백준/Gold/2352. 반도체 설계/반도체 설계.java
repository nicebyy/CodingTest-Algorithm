

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        dp = new int[arr.length];
        Arrays.fill(dp,1);
        dp[0] = 1;

        for(int i=1;i<arr.length;i++){

            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}


