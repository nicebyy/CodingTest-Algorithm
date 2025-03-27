
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MAX_DISTANCE = 1000*1000;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        char[] road = input.toCharArray();
        int[] dp = new int[road.length];

        Arrays.fill(dp, MAX_DISTANCE);
        dp[0] = 0;

        for(int i=0; i<road.length; i++){

            if(road[i] == 'B'){
                for(int j=i+1; j<road.length;j++){
                    if(road[j] == 'O'){
                        dp[j] = Math.min(dp[i]+(j-i)*(j-i), dp[j]);
                    }
                }
            }else if(road[i] == 'O'){
                for(int j=i+1; j<road.length;j++){
                    if(road[j] == 'J'){
                        dp[j] = Math.min(dp[i]+(j-i)*(j-i), dp[j]);
                    }
                }
            }else{
                for(int j=i+1; j<road.length;j++){
                    if(road[j] == 'B'){
                        dp[j] = Math.min(dp[i]+(j-i)*(j-i), dp[j]);
                    }
                }
            }
        }

        if(dp[n-1] == MAX_DISTANCE){
            dp[n-1] = -1;
        }
        System.out.println(dp[n-1]);
    }
}
