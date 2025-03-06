
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int maxHeight = 0;
    static int n;
    static int[] level;
    static int[][] parents; // node, height

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

            list.get(from).add(to);
            list.get(to).add(from);
        }
        maxHeight = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        level = new int[n + 1];
        parents = new int[n + 1][maxHeight];

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

            if (from == to) {
                stringBuilder.append(from).append("\n");
                continue;
            }

            for (int h = maxHeight - 1; h >= 0; h--) {
                if (parents[from][h] != parents[to][h]) {
                    from = parents[from][h];
                    to = parents[to][h];
                }
            }
            stringBuilder.append(parents[from][0]).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    public static void setLevelInit(int node, int depth, int parent) {

        level[node] = depth;
        for (Integer next : list.get(node)) {
            if (next != parent) {
                setLevelInit(next, depth + 1, node);
                parents[next][0] = node;
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
}
