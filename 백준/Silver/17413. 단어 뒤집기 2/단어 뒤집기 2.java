import static java.util.Arrays.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("");

        boolean stream=false;
        StringBuilder sb = new StringBuilder();
        StringBuilder reverseStr = new StringBuilder();
        for (String s : input) {
//            System.out.print(s);
            if(s.equals("<")){
                sb.append(reverseStr.reverse());
                sb.append(s);
                reverseStr = new StringBuilder();
                stream=true;
            }else if(s.equals(">")){
                sb.append(s);
                stream=false;
            }else if(stream){
                sb.append(s);
            }else{
                if(s.equals(" ")){
                    sb.append(reverseStr.reverse());
                    sb.append(s);
                    reverseStr = new StringBuilder();
                }else{
                    reverseStr.append(s);
                }
            }
        }
        sb.append(reverseStr.reverse());
        System.out.println(sb.toString());

    }
}