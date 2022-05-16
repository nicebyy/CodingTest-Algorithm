import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    static int n,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0]; k=input[1];

        int ans=0;

        while (true){

            int moreBottle=0;
            int minBottle = n;

            while (minBottle!=0){

                if(minBottle%2==1)
                    moreBottle++;
                minBottle = minBottle/2;
            }

            if(moreBottle <= k )
                break;
            n++;
            ans++;
        }

        System.out.println(ans);
    }
}
