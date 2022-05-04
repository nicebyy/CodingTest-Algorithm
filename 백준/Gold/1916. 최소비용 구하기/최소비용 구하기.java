import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		Graph g = new Graph(V,E);
		g.setList();
		g.readList(true, true, br);
		String input[];
		input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		int res = g.dijstra(start,end);
		System.out.println(res);


	}

}
class Node implements Comparable<Node>{
	int node;
	int weight;

	public Node(int node,int weight){
		this.node=node;
		this.weight=weight;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.weight-o.weight;
	}

}
class Graph {
	static int V,E;
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Node> list[];
	static int distance[];
	static boolean visit[];
	public Graph(int V,int E)
	{
		this.V=V;
		this.E=E;
	}
	public void setList()
	{
		list = new ArrayList[V+1];
		for(int i=1;i<=V;i++)
			list[i] = new ArrayList<Node>();
		visit = new boolean[V+1];
	}
	public void readList(boolean space,boolean directed,BufferedReader br) throws IOException {
		String input[];
		
		for(int i=1;i<=E;i++) 
		{
			if(space) {
				input = br.readLine().split(" ");
			}
			else {
				input = br.readLine().split("");
			}
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			
			if(directed)
				list[start].add(new Node(end,weight));
			else
			{
				list[start].add(new Node(end,weight));
				list[end].add(new Node(start,weight));
			}
		}
	}

	public int dijstra(int start,int end)
	{
		distance = new int[V+1];
		Queue<Node> pq = new PriorityQueue<Node>();
		Arrays.fill(distance,INF);
		distance[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty())
		{
			Node tmp = pq.poll();
			if(!visit[tmp.node])
			{
				visit[tmp.node] = true;
				for(Node node : list[tmp.node])
				{
					if(!visit[node.node] && distance[node.node] > distance[tmp.node] + node.weight)
					{
						distance[node.node] = distance[tmp.node] + node.weight;
						pq.add(new Node(node.node,distance[node.node]));
					}
				}
			}
		}
		

		
		return distance[end];
	}
}


