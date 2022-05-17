import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    static int n,m;
    static int[] trains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        m = input[1];
        trains = new int[n+1];

        for(int i=0;i<m;i++){ // command

            input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            int command = input[0];
            int index = input[1];
            int seat = input.length==2 ? 0 : input[2];

            if(command==1){
                trains[index] |= (1<<seat);
            }else if(command==2){
                trains[index] &= ~(1<<seat);
            }else if(command==3){
                trains[index] <<= 1;
                trains[index] &= ((1<<21)-1);
            } else if (command == 4) {
                trains[index] >>= 1;
                trains[index] &= ~1;
            }
        }

        int ans = (int) stream(trains,1,n+1)
                .distinct()
                .count();

        System.out.println(ans);
    }
}
