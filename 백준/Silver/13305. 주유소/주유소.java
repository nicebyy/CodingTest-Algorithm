
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] distances;
    static int[] costs;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        distances = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        costs = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int currentPosition = 0;
        long totalCost = 0;
        int chargePositionIndex = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1-o2);

        pq.add(costs[chargePositionIndex]);

        for(int i=0;i<distances.length;i++){

            totalCost += (long)pq.peek()*distances[i];
            chargePositionIndex++;
            pq.add(costs[chargePositionIndex]);
        }

        System.out.println(totalCost);
    }
}


