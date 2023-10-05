import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dp;
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(("0 "+br.readLine()).split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        dp = new int[n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i] = Math.max(dp[i],dp[i-j]+arr[j]);
            }
        }

        System.out.println(dp[n]);
    }
}

