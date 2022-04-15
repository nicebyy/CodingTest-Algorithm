import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = input[0], m = input[1];
        HashMap<String, String> memo = new HashMap<>();

        for(int i=0;i<n;i++){
            String[] info = br.readLine().split(" ");
            memo.put(info[0],info[1]);
        }
        for(int i=0;i<m;i++){
            System.out.println(memo.get(br.readLine()));
        }

    }

}

