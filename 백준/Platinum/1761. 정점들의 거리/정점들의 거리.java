
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<TreeNode>> list = new ArrayList<>();
    static int maxHeight = 0;
    static int n;
    static int[] level;
    static int[][] parents; // node, height
    static int[] distance;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            int[] input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = input[0];
            int to = input[1];
            list.get(from).add(new TreeNode(to, input[2]));
            list.get(to).add(new TreeNode(from, input[2]));
        }
        maxHeight = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        level = new int[n + 1];
        parents = new int[n + 1][maxHeight];
        distance = new int[n+1];

        setLevelInit(1, 1, 0);
        setParents();

        int queryCount = Integer.parseInt(br.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            int[] query = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = query[0];
            int to = query[1];

            if (level[from] < level[to]) {
                int temp = from;
                from = to;
                to = temp;
            }

            for (int h = maxHeight - 1; h >= 0; h--) {
                if (Math.pow(2, h) <= level[from] - level[to]) {
                    from = parents[from][h];
                }
            }
            int lca = 0;

            if (from == to) {
                lca = from;
            }else{
                for (int h = maxHeight - 1; h >= 0; h--) {
                    if (parents[from][h] != parents[to][h]) {
                        from = parents[from][h];
                        to = parents[to][h];
                    }
                }
                lca = parents[from][0];
            }
            stringBuilder.append(distance[query[0]]+distance[query[1]]-2*distance[lca]+"\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void setLevelInit(int node, int depth, int parent) {
        level[node] = depth;
        for (TreeNode next : list.get(node)) {
            if (next.num != parent) {
                distance[next.num] = distance[node] + next.distance;
                setLevelInit(next.num, depth + 1, node);
                parents[next.num][0] = node;
            }
        }
    }

    public static void setParents() {
        for (int exp = 1; exp < maxHeight; exp++) {
            for (int node = 1; node <= n; node++) {
                parents[node][exp] = parents[parents[node][exp - 1]][exp - 1];
            }
        }
    }

    static class TreeNode{
        int num;
        int distance;

        public TreeNode(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }
}


/**
 15
 1 2 10
 1 3 2
 1 4 5
 2 5 100
 5 9 10
 5 15 13
 9 13 8
 9 14 99
 3 6 1
 3 7 2
 4 8 102
 8 10 31
 8 11 117
 8 12 55
 5
 12 14
 6 7
 10 4
 13 15
 13 1
 */