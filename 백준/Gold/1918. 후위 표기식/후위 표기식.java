import java.io.*;
import java.util.*;
	public class Main {
		public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	Stack<Character> st = new Stack<Character>();
	    	char [] input = br.readLine().toCharArray();
	    	StringBuilder sb = new StringBuilder();
	    	for(int i=0;i<input.length;i++)
	    	{
	    		char a = input[i];
	    		if(a=='(')
	    			st.push(a);
	    		else if(a=='*'||a=='/')
	    		{
	    			while(!st.isEmpty()&&(st.peek()=='*'||st.peek()=='/'))
	    			{
	    				sb.append(st.pop());
	    			}
	    			st.push(a);
	    		}
	    		else if(a=='+'||a=='-')
	    		{
	    			while(!st.isEmpty()&&st.peek()!='(')
	    			{
	    				sb.append(st.pop());
	    			}
	    			st.push(a);
	    		}
	    		else if(a==')')
	    		{
	    			while(!st.isEmpty()&&st.peek()!='(')
	    			{
	    				sb.append(st.pop());
	    			}
	    			st.pop();
	    		}
	    		else
	    			sb.append(a);
	    	}
	    	while(!st.isEmpty())
	    		sb.append(st.pop());
	    	System.out.println(sb.toString());

		}


}