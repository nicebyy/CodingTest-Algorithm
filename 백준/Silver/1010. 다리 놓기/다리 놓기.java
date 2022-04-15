import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		int cnt = Integer.parseInt(br.readLine());
		while(cnt>0)
		{
			String input[] = br.readLine().split(" ");
			int n = Integer.parseInt(input[1]);
			int r = Integer.parseInt(input[0]);
			
			int arr[][] = new int[n+1][n+1];
			for(int i=1;i<=n;i++)
			{
				arr[i][0] =1;
				arr[i][i] = 1;
				for(int j=1;j<i;j++)
				{
					arr[i][j] = arr[i-1][j] + arr[i-1][j-1]; 
				}
			}
			System.out.println(arr[n][r]);
			cnt--;
		}
	}
}