import static java.util.Arrays.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<String> left = stream(br.readLine().split(""))
                .collect(Collectors.toCollection(Stack::new));
        Stack<String> right = new Stack<>();
        int queryCount = Integer.parseInt(br.readLine());

        for(int i=0;i<queryCount;i++){
            String[] input = br.readLine().split(" ");
            String query = input[0];

            if(query.equals("L") && !left.isEmpty()){
                right.add(left.pop());
            }else if(query.equals("D") && !right.isEmpty()){
                left.add(right.pop());
            }else if(query.equals("B") && !left.isEmpty()){
                left.pop();
            }else if(query.equals("P") ){
                left.add(input[1]);
            }
        }


        StringBuffer ans = new StringBuffer();
        left.forEach(ans::append);
        while (!right.isEmpty())
            ans.append(right.pop());
        System.out.println(ans);
    }
}