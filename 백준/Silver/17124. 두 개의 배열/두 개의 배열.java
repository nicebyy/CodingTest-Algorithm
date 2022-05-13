import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    static int aLen,bLen;
    static ArrayList<Integer> listA;
    static TreeSet<Integer> listB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        while (tc-->0){
            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            aLen = input[0];
            bLen = input[1];

            listA = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));
            listB = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toCollection(TreeSet::new)); // criteria

//            System.out.println("===========start===========");
            long sum = 0L;

            for (Integer e : listA) {
                
                boolean search = listB.contains(e);
                int result=e;

                if(!search){
                    listB.add(e);

                    Integer lower = listB.lower(e);
                    Integer higher = listB.higher(e);

                    if(lower == null){
                        result = higher;
                    }else if(higher==null){
                        result = lower;
                    }else{
                        int l = Math.abs(e - lower);
                        int h = Math.abs(e - higher);

                        if(l<=h)
                            result = lower;
                        else
                            result = higher;
                    }
                    listB.remove(e);
                }

                sum+=(long)result;
            }
            System.out.println(sum);
        }
    }
}

/**

 *
 */