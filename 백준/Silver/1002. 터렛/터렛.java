import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		String input[][] = new String[cnt][];
		for(int i=0;i<cnt;i++)
		{
			input[i] = br.readLine().split(" ");
		}
		
		for(int i=0;i<cnt;i++)
		{
			int x1 = Integer.parseInt(input[i][0]);
			int y1 = Integer.parseInt(input[i][1]);
			int r1 = Integer.parseInt(input[i][2]);
			int x2 = Integer.parseInt(input[i][3]);
			int y2 = Integer.parseInt(input[i][4]);
			int r2 = Integer.parseInt(input[i][5]);
			double d = (double) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));	
//			System.out.println("r1="+r1+" , r2="+r2+" , d="+d);
			if(d==0&&r1-r2==0)
				System.out.println(-1);
			else if(Math.abs(r1-r2)<d&&d<r1+r2)
				System.out.println(2);
			else if(Math.abs(r1-r2)>d||r1+r2<d)
				System.out.println(0);
			else
				System.out.println(1);
		}
		
	}
}