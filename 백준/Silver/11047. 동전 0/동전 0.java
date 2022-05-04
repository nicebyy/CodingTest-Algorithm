import java.io.*;
import java.util.*;
	 
	public class Main {
	 
	    public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	       String input [] = br.readLine().split(" ");
	       int cnt = Integer.parseInt(input[0]);
	       int total = Integer.parseInt(input[1]);
	       int [] token = new int[cnt];
	       for(int i=0;i<cnt;i++)
	    	  token[i]=Integer.parseInt(br.readLine());
	       int min=0;
	       int i=0;
	       while(total>0&&(token.length-1-i)>=0)
	       {
	    	   if(total-token[token.length-1-i]>=0)
	    		   {total = total-token[token.length-1-i];min++;}
	    	   else
	    		   i++;
	    	   
	       }
	       System.out.println(min);
	 
   }
	 
}