import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[1001][10];
        Arrays.fill(dp[1],1);

        for(int i=2;i<=n;i++){
            for(int j=0;j<=9;j++){
                for(int k=j;k<=9;k++){
                    dp[i][j] += dp[i-1][k];
                }
                dp[i][j] %= 10007;
            }
        }

        System.out.println(Arrays.stream(dp[n]).sum()%10007);
    }
}
