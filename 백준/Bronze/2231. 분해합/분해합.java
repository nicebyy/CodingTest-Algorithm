import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int length = input.length();
		int start = Integer.parseInt(input);
		
		start = start - 9*(length);
		if(start<=0)
			start = 0;
		for(int i=start;i<Integer.parseInt(input);i++)
		{

			int sum = i;
			String result[] = Integer.toString(i).split("");
			
			for(int j=0;j<result.length;j++)
				sum = sum+Integer.parseInt(result[j]);

			if(sum==Integer.parseInt(input)) {
				System.out.println(i);
				break;
			}
			if(i==Integer.parseInt(input)-1)
				System.out.println(0);
		}

	}


}
