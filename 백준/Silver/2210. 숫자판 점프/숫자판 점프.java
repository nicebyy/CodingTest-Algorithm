import java.util.*;
import java.io.*;

public class Main {

	static String[][] map;
	static HashSet<String> list = new HashSet<String>();
	static int cnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String input;

    	input = br.readLine();
    	cnt = input.split(" ").length;
    	
    	map = new String[cnt][cnt];
    	
    	for(int i=0;i<cnt;i++)
    	{
    		map[i] = input.split(" ");
    		if(i==cnt-1)
    			break;
    		input = br.readLine();
    	}
    	

    	
    	String s = "";
    	
    	for(int i=0;i<cnt;i++)
    	{
    		for(int j=0;j<cnt;j++)
    		{
    			DFS(i,j,0,s);
    		}
    	}
    	System.out.println(list.size());
    	
//    	Iterator iter = list.iterator();	
//    	while(iter.hasNext()) {
//    	    System.out.println(iter.next());
//    	}
    	
    }
    
    public static void DFS(int x,int y,int depth,String s)
    {
    	if(depth==6) {
    		list.add(s);
    	}
    	else
    	{
    		for(int i=0;i<4;i++)
    		{
    			int nextX = dx[i]+x;
    			int nextY = dy[i]+y;
    			
    			if(nextX<0 || nextY<0 || nextX>=cnt||nextY>=cnt)
    				continue;
    			
    			DFS(nextX,nextY,depth+1,s+map[x][y]);
    		}
    	}
    }

}
 