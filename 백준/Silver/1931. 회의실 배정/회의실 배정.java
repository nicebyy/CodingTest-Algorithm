
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] meetings;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        meetings = new int[n][2];

        for(int i=0;i<n;i++){
            meetings[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }

        Arrays.sort(meetings,(o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int curTime = 0;
        int result = 0;
        for (int[] meeting : meetings) {
//            System.out.println(Arrays.toString(meeting));
            if(meeting[0] < curTime)
                continue;

            curTime = meeting[1];
            result ++;
//            System.out.println(curTime);
//            System.out.println(Arrays.toString(meeting));
        }

        System.out.println(result);
    }

}


