import java.util.*;
import java.io.*;

public class Main {

	static int parent[];
	static List<Integer> list[];
	static boolean visit[];
	

    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int cnt = Integer.parseInt(br.readLine());
    	
    	parent = new int[cnt+1];
    	list = new ArrayList[cnt+1];
    	visit = new boolean[cnt+1];
    	
    	for(int i=1;i<=cnt;i++)
    		list[i] = new ArrayList<Integer>();
    	
    	
    	
    	String[] input;
    	for(int i=0;i<cnt-1;i++)
    	{
    		input = br.readLine().split(" ");
    		int a = Integer.parseInt(input[0]);
    		int b = Integer.parseInt(input[1]);
    		
    		list[a].add(b);
    		list[b].add(a);
    	}
    	
    	dfs(1);
    	
    	for(int i=2;i<=cnt;i++) {
    		System.out.println(parent[i]);
    	}
    	
    	
    }
    
    static void dfs(int input) {
    	
    	visit[input] = true;
    	
    	for(int i : list[input])
    	{
    		if(!visit[i]) {
	    		parent[i] = input;
	    		dfs(i);
    		}
    	}
    }


}

 