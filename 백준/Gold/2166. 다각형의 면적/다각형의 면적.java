import java.io.*;
import java.nio.MappedByteBuffer;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	static int N;
	static int x[],y[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		x = new int[N+1];
		y = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			String in[] = br.readLine().split(" ");
			x[i] = Integer.parseInt(in[0]);
			y[i] = Integer.parseInt(in[1]);
		}
		
		double sum=0;
		for(int i=1;i<=N;i++) {
			if(i==N)
				sum+=(long)(x[i]+x[1])*(y[i]-y[1]);
			else
				sum += (long)(x[i]+x[i+1])*(y[i]-y[i+1]);
		}
		sum = Math.abs(sum/2);
		System.out.printf("%.1f",sum);
	}


}
