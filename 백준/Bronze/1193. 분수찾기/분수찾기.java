import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(br.readLine());
		int i;
		for(i=1;input>0;i++)
		{
			input = input-i; 
		}
		i = i-1;
		input = input+i;

		if(i%2==0)
			System.out.println(input+"/"+(i-input+1));
		else
			System.out.println((i-input+1)+"/"+input);
		
	}
}

