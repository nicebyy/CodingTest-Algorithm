import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        
        int k = Integer.parseInt(br.readLine());

        int point = 0;
        int ans = 1;
        for(int i=0;i<k;i++){
            int hold = Integer.parseInt(br.readLine());
            ans = ans * dp[hold - point -1];
            point = hold;
        }
        ans = ans * dp[n-point];

        System.out.println(ans);
    }
}

/**
 *
 * 5C3 => 5C2 에서 하나를 더 뽑은 경우
 * +
 * 4C2 에서 하나를 더 뽑은 경우
 *
 */

