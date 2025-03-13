import java.io.*;
import java.util.*;


public class Main {

	static int map[][];
	static int n;
	static int color[];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	String input[];
    	map = new int[n][n];
    	color = new int[2];
    	for(int i=0;i<n;i++) {
    		input = br.readLine().split(" ");
    		for(int j=0;j<input.length;j++)
    			map[i][j] = Integer.parseInt(input[j]);
    	}
    	

    	div(0,0,n);
    	System.out.println(color[0]);
    	System.out.println(color[1]);
    	
    }
    
    static void div(int x,int y,int len) {
//    	System.out.println(x+","+y+"좌표에서 "+len+"크기만큼  분할");
    	int init = map[y][x];

    	if(len==1) {
    		color[map[y][x]]++;
//    		System.out.println(x+","+y+"좌표에서 크기1짜리 발견");
    	}
    	else {
        	if(!isSame(x,y,len)) {
        		
        		div(x,y,len/2);
        		div(x+len/2,y,len/2);
        		div(x+len/2,y+len/2,len/2);
        		div(x,y+len/2,len/2);
        	}
        	else {
        		color[map[y][x]]++;
//        		System.out.println(x+","+y+"좌표에서 크기"+len+"짜리 발견");
        	}
    	}

    	
    	
    }
    static boolean isSame(int x,int y,int len) {
    	
    	int init = map[y][x];
    	
    	for(int i=y;i<y+len;i++) {
    		for(int j=x;j<x+len;j++) {
    			if(map[i][j]!=init)
    				return false;
    		}
    	}
    	
    	return true;
    }

}
