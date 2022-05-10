import java.io.*;
import java.util.*;
	 
	public class Main {

		public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	int cnt = Integer.parseInt(br.readLine());	
	    	int point;
	    	String input;
	    	while(cnt>0)
	    	{
	    		point = -1;
	    		input = br.readLine();
	    		String ans="YES";
	    		char tmp[] = input.toCharArray();
	    		for(int i=0;i<tmp.length;i++)
	    		{
	    			char a = tmp[i];
	    			if(a=='(')
	    				point++;
	    			else if(a==')')
	    				point--;
	    			else
	    				continue;
	    			if(point<-1) {
	    				ans="NO";break;}
	    		}
	    		if(point!=-1||ans.equals("NO"))
	    			System.out.println("NO");
	    		else
	    			System.out.println("YES");
	    		cnt--;
	    	}	
		}

}
