import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = (" "+br.readLine()).toCharArray();
        char[] arr2 = (" "+br.readLine()).toCharArray();
        int[][] dp = new int[arr1.length+1][arr2.length+1];

        for(int i=1;i<arr1.length;i++){
            for(int j=1;j<arr2.length;j++){

                if(arr1[i] == arr2[j]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[arr1.length-1][arr2.length-1]);
    }
}
