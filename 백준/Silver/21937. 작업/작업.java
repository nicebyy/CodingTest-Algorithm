

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input[0];
        int m = input[1];


        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            list.get(input[1] - 1).add(input[0] - 1);
        }

        int target = Integer.parseInt(br.readLine()) - 1;
        boolean[] visit = new boolean[n];
        int totalTask = dfs(target, list, visit);

        System.out.println(totalTask - 1);
    }

    public static int dfs(int cur, ArrayList<ArrayList<Integer>> list, boolean[] visit) {

        int task = 1;
        visit[cur] = true;

        for (Integer next : list.get(cur)) {
            if (!visit[next]) {
                task += dfs(next, list, visit);
            }
        }
        return task;
    }
}
