import java.io.*;

	
	
public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		if(input.equals("0"))
			System.out.println(1);
		else {
		String input2 = input;
		int cnt=0;
		int k=0;
		while(!input2.equals(Integer.toString(k))) {
		if(input.length()==1)
			input="0"+input;
		int a = Integer.parseInt(input.split("")[0])+Integer.parseInt(input.split("")[1]);
		k = (Integer.parseInt(input)%10)*10+a%10;

		cnt++;
		input = Integer.toString(k);
		}
		
		System.out.println(cnt);}
	}
}

