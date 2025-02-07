
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n;
    public static int[][] lectures;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        lectures = new int[n][2];

        for(int i=0; i<n; i++){
            lectures[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }

        Arrays.sort(lectures,(lec1,lec2) -> {
            if(lec1[0] == lec2[0]){
                return lec1[1]-lec2[1];
            }
            return lec1[0] - lec2[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((lec1,lec2) -> {
            return lec1[1]-lec2[1];
        });
        int maxRoom = 0;
        for (int[] lecture : lectures) {

            while (!pq.isEmpty()){
                if(pq.peek()[1] <= lecture[0]){
                    pq.poll();
                }else{
                    break;
                }
            }
            pq.add(lecture);
            maxRoom = Math.max(maxRoom, pq.size());
        }
        System.out.println(maxRoom);
    }
}
