
import java.io.*;
import java.util.Scanner;

public class Main {

    static int n,k;
    static int[][] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        n = sc.nextInt();
        k = sc.nextInt();
        dp = new int[1001][1001];
        System.out.println(combination(n,k));
    }

    public static int combination(int n,int k){

        if(n==k || k==0){
            return 1;
        }else if(dp[n][k] != 0){
            return dp[n][k];
        }
        return dp[n][k] = (combination(n-1,k-1)+combination(n-1,k))%10007;
    }
}
