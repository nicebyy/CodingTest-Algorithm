import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cnt = Integer.parseInt(br.readLine());

		long arr[] = new long[1000002];
		arr[0]=0;arr[1]=1;
		for(int i=0;i<cnt;i++)
		{
			arr[i+2] = arr[i] + arr[i+1];
			arr[i+2] %=15746;
		}
//		System.out.println((arr[cnt]+arr[cnt-1])%15746);
		System.out.println(arr[cnt+1]);
		
		}	
	
}






