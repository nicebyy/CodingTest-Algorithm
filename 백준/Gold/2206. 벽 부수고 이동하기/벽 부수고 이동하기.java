import java.io.*;
import java.util.*;
class Point{
	int x;
	int y;
	int cnt;
	boolean destroy;
	public Point(int x,int y,int cnt,boolean destroy) {
		this.x=x;
		this.y=y;
		this.cnt=cnt;
		this.destroy=destroy;
	}
}
public class Main {

	static int w,h;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans;
	public static boolean bfs(int x, int y) {

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x,y,1,false));
		

		while(!q.isEmpty()) {
			Point tmp = q.poll();

			if(tmp.x==w-1 && tmp.y==h-1) {
				ans = tmp.cnt;
				return true;
			}
			
			for(int dir=0;dir<4;dir++) {
				int r = tmp.x + dx[dir];
				int c = tmp.y + dy[dir];
				int cnt = tmp.cnt;
				
				if( r >=0 && c >=0 && r<w && c<h) {

					if(map[c][r] == 0 ) { 
											
						if(tmp.destroy==true && !visit[c][r][1]) {
							q.offer(new Point(r,c,cnt+1,tmp.destroy));
//							System.out.println("Q정보(c,r,cnt,des) : ("+c+","+r+"),  "+(int)(cnt+1)+","+tmp.destroy+"  이전 좌표 : ("+tmp.y+","+tmp.x+")");
							visit[c][r][1] = true;
						}
						else if(tmp.destroy==false && !visit[c][r][0]){
							q.offer(new Point(r,c,cnt+1,tmp.destroy));
//							System.out.println("Q정보(c,r,cnt,des) : ("+c+","+r+"),  "+(int)(cnt+1)+","+tmp.destroy+"  이전 좌표 : ("+tmp.y+","+tmp.x+")");
							visit[c][r][0] = true;
						}

					}
					else if(map[c][r]==1 && tmp.destroy==false && !visit[c][r][1] ) {
						q.offer(new Point(r,c,cnt+1,true));
//						System.out.println("Q정보(c,r,cnt,des) : ("+c+","+r+"),  "+(int)(cnt+1)+","+true+"  이전 좌표 : ("+tmp.y+","+tmp.x+")");
						visit[c][r][1] = true;
					}
				}
			}
			
		}
		return false;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[];
		input = br.readLine().split(" ");
		h = Integer.parseInt(input[0]); w=Integer.parseInt(input[1]);
		map = new int[h][w];
		visit = new boolean[h][w][2];
		for(int i=0;i<h;i++) {
			input = br.readLine().split("");
			for(int j=0;j<w;j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		boolean result;
		result = bfs(0,0);
		if(result == false)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
}