import java.io.*;
import java.util.*;

public class Main {
	public static class Planet {
		int idx;
		int x, y, z;
		public Planet(int idx, int x, int y, int z) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static int N;
	public static int ans;
	public static int[] p;
	public static Planet[] planets;
	public static PriorityQueue<int[]> pq;
	
	public static void union(int x, int y) {
		p[findSet(y)] = p[findSet(x)];
	}
	public static int findSet(int x) {
		if (p[x] == x) return x;
		return p[x] = findSet(p[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		p = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i, x, y, z);
			p[i] = i;
		}
		
		createEdge();
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (findSet(cur[0]) != findSet(cur[1])){
				ans+=cur[2];
				if (++cnt == N-1) break;
				union(cur[0], cur[1]);
			}
		}
		
		System.out.println(ans);
	}
	
	public static void createEdge() {
		Arrays.sort(planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.x, o2.x);
			}
		});
		for (int i = 1; i < N; i++) {
			int dis = Math.abs(planets[i].x-planets[i-1].x);
			pq.add(new int[] {planets[i].idx, planets[i-1].idx, dis});
		}
		
		Arrays.sort(planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.y, o2.y);
			}
		});
		for (int i = 1; i < N; i++) {
			int dis = Math.abs(planets[i].y-planets[i-1].y);
			pq.add(new int[] {planets[i].idx, planets[i-1].idx, dis});
		}
		
		Arrays.sort(planets, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return Integer.compare(o1.z, o2.z);
			}
		});
		for (int i = 1; i < N; i++) {
			int dis = Math.abs(planets[i].z-planets[i-1].z);
			pq.add(new int[] {planets[i].idx, planets[i-1].idx, dis});
		}
	}
}