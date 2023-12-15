import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<int[]>> list = new ArrayList<>();
        for(int i=0;i<=n;i++) list.add(new ArrayList<>());
        for(int i=1;i<=m;i++){
            int[] edge = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            list.get(edge[0]).add(new int[]{edge[1],edge[2]});
        }
        int[] point = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dist = new int[n+1]; Arrays.fill(dist,Integer.MAX_VALUE); dist[point[0]]=0;
        int[] path = new int[n+1];

        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]); pq.add(new int[]{point[0],0});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;
            for (int[] next : list.get(cur[0])) {

                if(dist[next[0]] > dist[cur[0]]+ next[1]){
                    dist[next[0]] = dist[cur[0]]+ next[1];
                    pq.add(new int[]{next[0],dist[next[0]]});
                    path[next[0]] = cur[0];
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        int cnt=0;
        for(int i=point[1];i!=0;i=path[i]){
            cnt++;
            ans.insert(0,i+" ");
        }

        ans.insert(0,cnt+"\n");
        ans.insert(0,dist[point[1]]+"\n");
        System.out.println(ans);
    }
}

