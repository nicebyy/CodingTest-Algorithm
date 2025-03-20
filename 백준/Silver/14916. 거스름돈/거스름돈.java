
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX = 1234567;
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[]{2,5};
        int[] dp = new int[100001];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i=1; i<dp.length; i++){
            for (int coin : coins) {
                if(i-coin>=0){
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        int ans = dp[n] == MAX ? -1 : dp[n];
        System.out.println(ans);
    }
}