import java.io.*;
import java.util.*;



public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		int arr[] = new int[cnt+1];
		int dp[] = new int[cnt+1];
		String[] input = br.readLine().split(" ");
		for(int i=0;i<cnt;i++)
			arr[i+1] = Integer.parseInt(input[i]);
		
		int max=0;
		for(int i=1;i<=cnt;i++)
		{
			dp[i] = 1;
			
			for(int j=1;j<i;j++)
			{
				if(arr[j]<arr[i] && dp[i]<dp[j]+1)
					dp[i] = dp[j]+1;
			}
			if(max < dp[i])
			{
				max = dp[i];
			}
		}
		
		System.out.println(max);
	}


}
