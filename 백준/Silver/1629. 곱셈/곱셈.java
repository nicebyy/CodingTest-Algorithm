
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int n = input[0];
        int m = input[1];
        int d = input[2];

        long result = customPow(n, m, d);
        System.out.println(result);
    }

    static long customPow(long num,long exp,long mod){

        if(exp == 0){
            return 1;
        }

        long half = customPow(num, exp / 2, mod);

        if(exp % 2 == 0){
            return half * half % mod;
        }
        return(half * half % mod) * num%mod;
    }
}


/**
 *
 *
 * 10 * 10 * 10 % 7
 *
 * == (10%7) ^ 2 * (10%7) % 7
 */