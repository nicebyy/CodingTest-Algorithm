import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        int[] dp = arr.clone();

        for(int i=0;i<n;i++){

            for(int j=0;j<i;j++){

                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+arr[i]);
                }
            }
        }

        int result = Arrays.stream(dp).max().orElse(0);
        System.out.println(result);
    }
}
