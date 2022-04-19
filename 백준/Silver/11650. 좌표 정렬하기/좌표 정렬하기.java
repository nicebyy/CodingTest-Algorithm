import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		ArrayList<String> list= new ArrayList<String>();
		for(int i=0;i<cnt;i++)
			list.add(br.readLine());
		
		  Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(Integer.parseInt(o1.split(" ")[0])<Integer.parseInt(o2.split(" ")[0]))
						return -1;
					else if(Integer.parseInt(o1.split(" ")[0])>Integer.parseInt(o2.split(" ")[0]))
						return 1;
					else if(Integer.parseInt(o1.split(" ")[0])==Integer.parseInt(o2.split(" ")[0]))
					{
						if(Integer.parseInt(o1.split(" ")[1])<Integer.parseInt(o2.split(" ")[1]))
							return -1;
						else
							return 1;
					}
					return 0;
						
				}
	        });
		  for(int i=0;i<list.size();i++)
			  System.out.println(list.get(i));
	}
}



