import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-->0){

            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            for(int i=0;i<2;i++)
                arr[i] = Arrays.stream(br.readLine().split(" "))
                         .mapToInt(Integer::parseInt).toArray();
            int[][] dp = new int[2][n];

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            
            if(n>1){
                dp[0][1] = arr[0][1]+arr[1][0];
                dp[1][1] = arr[1][1]+arr[0][0];   
            }

            for(int i=2;i<n;i++){

                dp[0][i] = arr[0][i] + Math.max(dp[1][i-1],dp[1][i-2]);
                dp[1][i] = arr[1][i] + Math.max(dp[0][i-1],dp[0][i-2]);

            }
            int result = Math.max(Arrays.stream(dp[0]).max().orElse(0), Arrays.stream(dp[1]).max().orElse(0));
            System.out.println(result);
        }
    }
}
