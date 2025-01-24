
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final String PASS = "1\n";
    public static final String FAIL = "0\n";

    public static void main(String[] args) throws IOException {

        int tc = Integer.parseInt(br.readLine());

        while (tc --> 0 ){

            int n = Integer.parseInt(br.readLine());
            int[] answers = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            Arrays.sort(answers);

            int k = Integer.parseInt(br.readLine());

            int[] submits = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            StringBuilder result = new StringBuilder();
            for (int submit : submits) {

                int pos = Arrays.binarySearch(answers, submit);

                if(pos >= 0){
                    result.append(PASS);
                }else{
                    result.append(FAIL);
                }
            }
            System.out.print(result.toString());
        }
    }
}
