import java.io.*;
import java.util.*;
	 
	public class Main {
	 
	    public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	      int cnt = Integer.parseInt(br.readLine());
	      String input[] = br.readLine().split(" ");
	      int tmp[] = new int[cnt];
	      for(int i=0;i<cnt;i++)
	    	  tmp[i] = Integer.parseInt(input[i]);
	      Arrays.sort(tmp);
	      int sum=0;
	      int sum2=0;
	      for(int i=0;i<cnt;i++) {
	    	  sum=sum+tmp[i];
	    	  sum2 +=sum;
	      }
	      System.out.println(sum2);
	 
   }
	 
}