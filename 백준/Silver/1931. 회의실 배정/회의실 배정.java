import java.io.*;
import java.util.*;
	 
	public class Main {

		public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    	int cnt = Integer.parseInt(br.readLine());
	    	String input;
	    	int[][]arr = new int[cnt][2];
	    	for(int i=0;i<cnt;i++) {
	    		input = br.readLine();
	    		arr[i][0] = Integer.parseInt(input.split(" ")[0]);
	    		arr[i][1] = Integer.parseInt(input.split(" ")[1]);
	    	}
	    	
	    	Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[1]<o2[1])
						return -1;
					else if(o1[1]==o2[1])
						return o1[0]<=o2[0]? -1:1;
					return 1;
				}});
				
	    	int count = 1;
			int[] prev = arr[0];
			for(int i = 1; i < cnt; i++) {
				if(prev[1] <= arr[i][0]) {
					count++;
					prev = arr[i];
				}
			}
			System.out.println(count);

	   
   }
}
