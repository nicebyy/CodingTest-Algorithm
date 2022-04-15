import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-->0){
            int sum=0;
            int n = Integer.parseInt(br.readLine());
            for(int i=5;i<=n;i=i*5){
                sum+=n/i;
            }
            System.out.println(sum);
        }
    }
}

