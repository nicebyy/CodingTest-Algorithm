
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] cards = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] even = new int[n/2+1];
        int[] odd = new int[n/2+1];
//
        even[0] = cards[0];
        odd[0] = cards[1];

        for(int i=2;i<cards.length;i++){

            if(i%2 == 0){
                even[i/2] = even[i/2-1]+cards[i];
            }else{
                odd[i/2] = odd[i/2-1]+cards[i];
            }
        }

        int maxValue = Math.max(even[n/2-1],odd[n/2-1]);
        int secondFakeValue = even[0] + odd[n/2-1] - odd[0];
        maxValue = Math.max(maxValue,secondFakeValue);

        for(int i=2;i<=cards.length;i++){

            int nextValue;
            if(i%2 == 0){
                nextValue = even[i/2-1] + odd[n/2-1]-odd[i/2-1];
            }else{
                nextValue = even[i/2] + odd[n/2-2]-odd[i/2-1];
            }
            maxValue = Math.max(maxValue,nextValue);
        }

        System.out.println(maxValue);
    }

}

/**
 *
 * 4
 * 1 2 3 4
 * = 6
 *
 * 6
 * 1 2 3 4 5 6
 * = 12
 *
 * 2
 * 1 2
 * = 2
 *
 * 6
 * 3 2 5 2 1 3
 * = 11
 *
 * 6
 * 1 2 100 80 5 2
 * 181
 
 */


