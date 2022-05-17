import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = Integer.parseInt(br.readLine());
		String tmp[] = new String[cnt];
		for(int i=0;i<cnt;i++)
			tmp[i] = br.readLine();
		for(int i=0;i<cnt;i++) {
			String a[] = tmp[i].split("");
			
			int score =0;
			int stream =0;
			for(int j=0;j<a.length;j++)
			{
				if(a[j].equals("O"))
				{
					stream++;
					score +=stream;
				}
				else
				{
					stream=0;
				}
			}
			System.out.println(score);
		}
		
	}
}