import static java.util.Arrays.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<String> set = new TreeSet<>(String::compareTo); // 사전순 정렬 조건 set
        StringBuilder sb = new StringBuilder().append(br.readLine());

        for(int i=1;i<sb.length()-1;i++){
            for(int j=i+1;j<sb.length();j++){
                
                StringBuilder append = new StringBuilder()
                        .append(new StringBuilder().append(sb.substring(0, i)).reverse())
                        .append(new StringBuilder().append(sb.substring(i, j)).reverse())
                        .append(new StringBuilder().append(sb.substring(j, sb.length())).reverse());
                set.add(append.toString());
            }
        }
        System.out.println(set.first());
    }

}