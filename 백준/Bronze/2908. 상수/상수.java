import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp[] = br.readLine().split(" ");
		StringBuffer a = new StringBuffer(tmp[0]);
		StringBuffer b = new StringBuffer(tmp[1]);
		a = a.reverse();
		b = b.reverse();
		if(Integer.parseInt(a.toString())>Integer.parseInt(b.toString()))
			System.out.println(a);
		else
			System.out.println(b);
		
	}
}