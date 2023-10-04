import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int n;
    static int[] dp;
    static Consult[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n+2];
        arr = new Consult[n+2];
        for(int i=1;i<=n;i++){
            arr[i] = new Consult(br.readLine());
        }
        arr[n+1] = new Consult("0 0");

        int maxPay = Integer.MIN_VALUE;
        for(int i=1;i<=n+1;i++){

            if(maxPay < dp[i]){
                maxPay = dp[i];
            }

            if(arr[i].days+i > n+1)
                continue;

            dp[arr[i].days+i] = Math.max(dp[arr[i].days+i],maxPay+arr[i].pays);
        }

        System.out.println(maxPay);
    }

    static class Consult{
        int days;
        int pays;

        public Consult(String in) {
            int[] input = Arrays.stream(in.split(" "))
                     .mapToInt(Integer::parseInt).toArray();
            this.days = input[0];
            this.pays = input[1];
        }
    }
}
