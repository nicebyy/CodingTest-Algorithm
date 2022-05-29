import java.io.*;
import java.util.*;



public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		int day[] = new int[cnt+10];
		int pay[] = new int[cnt+10];
		int dp[] = new int[cnt+10];
		String[] input;
		for(int i=1;i<=cnt;i++)
		{
			input = br.readLine().split(" ");
			day[i] = Integer.parseInt(input[0]);
			pay[i] = Integer.parseInt(input[1]);
		}
		int max = 0;
		for(int i=1;i<=cnt+1;i++)
		{
			dp[i] = Math.max(dp[i], max);
			dp[day[i]+i] = Math.max(dp[day[i]+i], pay[i]+dp[i]);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}


}
