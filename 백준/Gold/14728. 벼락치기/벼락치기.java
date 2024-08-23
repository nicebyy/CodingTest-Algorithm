
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
        int time = input[1];

        int[][] dp = new int[n+1][time+1];
        int[][] arr = new int[n+1][2];

        for(int i=1;i<=n;i++){
            input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            arr[i][0] = input[0];
            arr[i][1] = input[1];
        }

        for(int i=1;i<=n;i++){

            for(int j=0;j<=time;j++){

                if(arr[i][0] <= j){
                    dp[i][j] = Math.max(arr[i][1] + dp[i-1][j-arr[i][0]],dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n][time]);
    }

}

