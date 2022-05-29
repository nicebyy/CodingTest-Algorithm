import java.io.*;
import java.util.*;



public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp[] = br.readLine().split(" ");
		
		int n = Integer.parseInt(tmp[0]);
		int k = Integer.parseInt(tmp[1]);
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		int coin;
		for(int i=0;i<n;i++) {
			coin = Integer.parseInt(br.readLine());
			for(int j=1;j<k+1;j++)
			{
				if(j>=coin)
					dp[j] +=dp[j-coin];
			}
		}
		System.out.println(dp[k]);
		
	}
	

}
