
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int turn = 0;
    static ArrayDeque<String> stacks = new ArrayDeque<String>();
    static int totalScore = 0;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split("");

        for (String cur : input) {

            if(checkOCond(cur) || checkXCond(cur)){
                stacks.pollLast();
            }else{
                stacks.addLast(cur);
                turn = Math.max(turn,stacks.size());
            }
        }
        if(!stacks.isEmpty()){
            turn = -1;
        }
        System.out.println(turn);
    }

    static boolean checkXCond(String c){
        return c.equals("(") && !stacks.isEmpty() && stacks.peekLast().equals(")");
    }

    static boolean checkOCond(String c){
        return c.equals(")") && !stacks.isEmpty() && stacks.peekLast().equals("(");
    }
}
