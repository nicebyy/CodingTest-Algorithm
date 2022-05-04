import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		Point list[] = new Point[cnt];
		for(int i=0;i<cnt;i++)
		{
			String tmp[] = br.readLine().split(" ");
			int x = Integer.parseInt(tmp[0]);
			int y = Integer.parseInt(tmp[1]);
			list[i] = new Point(x,y);
		}

		for(int i=0;i<cnt;i++)
		{
			for(int j=0;j<cnt;j++)
			{
				if(list[i].x > list[j].x && list[i].y >list[j].y)
					list[j].Order = list[j].Order+1;
			}
		}
		
		for(int i=0;i<cnt;i++)
			System.out.println(list[i].Order);

	}


}
class Point{

	int x;
	int y;
	int Order;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
		this.Order=1;
	}
	
}