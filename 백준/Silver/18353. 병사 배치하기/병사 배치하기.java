
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static int[] arr;
    static int n;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int[] dp = new int[n];
        Arrays.fill(dp,1);

        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){

                if(arr[i] < arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        System.out.println(n-Arrays.stream(dp).max().getAsInt());
    }
}
