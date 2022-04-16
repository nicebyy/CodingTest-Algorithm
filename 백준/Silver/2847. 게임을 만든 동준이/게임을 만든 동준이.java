import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static Stack<Integer> st = new Stack<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++)
            st.push(Integer.parseInt(br.readLine()));

        int preScore=st.pop();
        int downCount = 0;

        while (!st.isEmpty()){
            Integer cur = st.pop();
//            System.out.println(preScore);
            if(preScore<=cur){
                int diff = (cur-preScore)+1;
                preScore=cur-diff;
                downCount+=diff;
            }else
                preScore = cur;
        }
        System.out.println(downCount);
    }
}

