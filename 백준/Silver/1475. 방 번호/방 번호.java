import java.io.*;
import java.util.*;



public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("");
		int num[] = new int[10];
		int flag=6;
		for(String str : input)
		{
			
			if(Integer.parseInt(str)==6 || Integer.parseInt(str) ==9)
			{
				num[6]++;
			}
			else
				num[Integer.parseInt(str)]++;
		}
		
		num[6] = num[6]/2+num[6]%2;
		
		int max =0;
		
		for(int i : num)
			if(i>max)
				max = i;
		System.out.println(max);
		
	}
	

}
