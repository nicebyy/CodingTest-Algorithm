import java.io.*;
import java.util.*;
	public class Main {
		public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	int cnt = Integer.parseInt(br.readLine());
	    	PriorityQueue<Long> q = new PriorityQueue<Long>();
	    	long input=0;
	    	while(cnt>0)
	    	{
	    		
	    		input = Long.parseLong(br.readLine());
	    		if(input==0)
	    		{
	    			if(q.size()==0)
	    				System.out.println(0);
	    			else
	    				System.out.println(q.poll());
	    		}
	    		else
	    		{
	    			q.add(input);
	    		}
	    		cnt--;
	    	}
	}
}