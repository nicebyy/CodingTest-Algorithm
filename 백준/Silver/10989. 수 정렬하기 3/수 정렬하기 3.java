import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int cnt = Integer.parseInt(br.readLine());
		int arr[] = new int[10001];
		for(int i=0;i<cnt;i++)
		{
			arr[Integer.parseInt(br.readLine())]++;
		}
		for(int i=0;i<10001;i++) {
			if(arr[i]>0) {
				bw.write(Integer.toString(i)+"\n");
				arr[i]--;i--;
				}
		}
		bw.flush();
	}
}



