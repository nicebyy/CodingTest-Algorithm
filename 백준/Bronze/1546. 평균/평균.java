import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		int cnt = Integer.parseInt(br.readLine());
		String sub[] = br.readLine().split(" ");
		for(int i=0;i<cnt;i++) {
			list.add(Integer.parseInt(sub[i]));
		}
		Collections.sort(list);

		
		double max=(double)list.get(cnt-1);
		double sum=0;
		for(int i=0;i<cnt;i++) {
			sum += (double)list.get(i)/max*100;}
		System.out.println(sum/(double)cnt);
		
	}
}