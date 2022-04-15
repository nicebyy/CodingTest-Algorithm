import java.io.*;
import java.util.*;
		 
	public class Main {
	
		public static void main(String[] args) throws NumberFormatException, IOException {
		   	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   	int cnt = Integer.parseInt(br.readLine());
		   	for(int i=0;i<cnt;i++)
		   	{
		   		int tmp = Integer.parseInt(br.readLine());
		    	HashMap<String,Integer> map = new HashMap<>();
		    	for(int j=0;j<tmp;j++)
		    	{			
		    		String s = br.readLine().split(" ")[1];
		    		if(map.containsKey(s))
		    			map.put(s,map.get(s)+1);
		    		else    			
		    			map.put(s,1);
		    	}
		    	int sum=1;
		    	for(int val : map.values()) {
		    		sum *= (val+1);
		    	}
		    	System.out.println(sum-1);
		   	}
	   }
}