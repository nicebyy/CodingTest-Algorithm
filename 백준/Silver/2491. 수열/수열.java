import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = stream((br.readLine()).split(" "))
                  .mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[2][n];
        for(int i=0;i<2;i++) Arrays.fill(dp[i],1);

        for(int i=1;i<n;i++){
            if(arr[i]>=arr[i-1]){
                dp[0][i]=dp[0][i-1]+1;
            }
            if(arr[i]<=arr[i-1]){
                dp[1][i]=dp[1][i-1]+1;
            }
        }

        int dp1 = stream(dp[0]).max().getAsInt();
        int dp2 = stream(dp[1]).max().getAsInt();
        System.out.println(Math.max(dp1,dp2));

    }
}

