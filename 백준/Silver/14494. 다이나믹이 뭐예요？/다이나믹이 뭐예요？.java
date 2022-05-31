import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

import static java.util.Arrays.stream;

public class Main {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int n = input[0];
        int m = input[1];
        int[][] dp = new int[n+1][m+1];

        dp[0][0]=1;
        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){
                dp[i][j] = ((dp[i-1][j-1]+dp[i-1][j])%MOD+dp[i][j-1])%MOD;
            }
        }

        System.out.println(dp[n][m]);
    }
}
