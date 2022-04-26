import java.io.*;
import java.util.*;
	 
	public class Main {
	 
	    public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String input[] = br.readLine().split("-");
	    if(input.length==1)
	    {
	    	int sum=0;
	    	String tmp[] = input[0].split("\\+");
	    	for(int i=0;i<tmp.length;i++)
	    		sum+=Integer.parseInt(tmp[i]);
	    	System.out.println(sum);
	    }
	    else {
	    	int min=0;
	    	if(input[0].split("\\+").length>1)
	    	{
	    		String tmp[] = input[0].split("\\+");
	    		for(int j=0;j<tmp.length;j++)
	    		{
	    			min +=Integer.parseInt(tmp[j]);
	    		}
	    	}
	    	else {
	    		min +=Integer.parseInt(input[0]);
	    	}
	    	
	
	    for(int i=1;i<input.length;i++)
	    {

	    	if(input[i].split("\\+").length>1)
	    	{
	    		String tmp[] = input[i].split("\\+");
	    		for(int j=0;j<tmp.length;j++)
	    		{
	    			min -=Integer.parseInt(tmp[j]);
	    		}
	    	}
	    	else {
	    		min -=Integer.parseInt(input[i]);
	    	}
	    }
	    System.out.println(min);
	    }
   }
	 
}