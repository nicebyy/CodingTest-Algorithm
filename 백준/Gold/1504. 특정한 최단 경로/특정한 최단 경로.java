
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int n,e;
    static int[] distance;
    static int[] targetNode;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0]; e = input[1];

        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<e;i++){
            input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            list.get(input[0]).add(new Node(input[1],input[2]));
            list.get(input[1]).add(new Node(input[0],input[2]));
        }
        targetNode = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int[] startDistance = findShortPath(1);
        int[] targetNodeV1Distance = findShortPath(targetNode[0]);
        int[] targetNodeV2Distance = findShortPath(targetNode[1]);

        int startToTargetV1 = startDistance[targetNode[0]];
        int startToTargetV2 = startDistance[targetNode[1]];

        int targetNodeV1ToTargetNodeV2 = targetNodeV1Distance[targetNode[1]];

        int targetNodeV2ToEnd = targetNodeV2Distance[n];
        int targetNodeV1ToEnd = targetNodeV1Distance[n];

        boolean checkStartToV1ToV2ToEnd = startToTargetV1 == Integer.MAX_VALUE || targetNodeV1ToTargetNodeV2 == Integer.MAX_VALUE || targetNodeV2ToEnd == Integer.MAX_VALUE;
        boolean checkStartToV2ToV1ToEnd = startToTargetV2 == Integer.MAX_VALUE || targetNodeV1ToTargetNodeV2 == Integer.MAX_VALUE || targetNodeV1ToEnd == Integer.MAX_VALUE;

        int result = 0;
        if(checkStartToV1ToV2ToEnd && checkStartToV2ToV1ToEnd)
            result = -1;
        else if(!checkStartToV1ToV2ToEnd && checkStartToV2ToV1ToEnd){
            result = startToTargetV1 + targetNodeV1ToTargetNodeV2 + targetNodeV2ToEnd;
        }else if(checkStartToV1ToV2ToEnd && !checkStartToV2ToV1ToEnd){
            result = startToTargetV2 + targetNodeV1ToTargetNodeV2 + targetNodeV1ToEnd;
        }else{
            result = Math.min(
                    startToTargetV1 + targetNodeV1ToTargetNodeV2 + targetNodeV2ToEnd,
                    startToTargetV2 + targetNodeV1ToTargetNodeV2 + targetNodeV1ToEnd
            );
        }

        System.out.println(result);
//        System.out.println(Arrays.toString(distance));
    }

    private static int[] findShortPath(int startNode) {

        int[] distance = new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode,0));
        distance[startNode] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node next : list.get(cur.node)) {

                if(distance[next.node] > distance[cur.node] + next.weight){
                    distance[next.node] = distance[cur.node] + next.weight;
                    pq.add(new Node(next.node,distance[next.node]));
                }
            }
        }

        return distance;
    }

    static class Node implements Comparable<Node>{
        int node;
        int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
