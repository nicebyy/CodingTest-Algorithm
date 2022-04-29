import static java.util.Arrays.*;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int m = input[1];

        BigInteger[][] comb = new BigInteger[101][101];
        for(int i=0;i<=100;i++){
            for(int j=0;j<=100;j++){
                comb[i][j] = BigInteger.valueOf(1L);
            }
        }

        for(int i=2;i<=100;i++){

            for(int j=1;j<i;j++){
                comb[i][j] = comb[i-1][j].add(comb[i-1][j-1]);
            }
        }
        System.out.println(comb[n][m].toString());

    }

}