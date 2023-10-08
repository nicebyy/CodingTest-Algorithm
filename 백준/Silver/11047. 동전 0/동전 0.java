import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int k = input[1];
        int[] coins = new int[n];
        for(int i=0;i<n;i++)
            coins[i] = Integer.parseInt(br.readLine());
        Arrays.sort(coins);

        int cnt = 0;
        for(int i=coins.length-1;i>=0;i--){

            if(k - coins[i] >= 0){
                k -= coins[i];
                cnt++;
                i++;
            }
        }
        System.out.println(cnt);
    }
}
