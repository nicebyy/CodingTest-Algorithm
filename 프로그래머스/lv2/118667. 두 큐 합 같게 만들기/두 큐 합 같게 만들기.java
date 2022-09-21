import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        int tried = 0;

        long sum1 = stream(queue1).sum();
        long sum2 = stream(queue2).sum();

        LinkedList<Integer> q1 = stream(queue1).boxed().collect(toCollection(LinkedList::new));
        LinkedList<Integer> q2 = stream(queue2).boxed().collect(toCollection(LinkedList::new));
        

        while (sum1!=sum2 && tried<=(queue1.length*3)){
            
            if(sum1 > sum2){
                Integer poll = q1.poll();
                sum2 += poll;
                sum1 -= poll;
                q2.add(poll);
            }else{
                Integer poll = q2.poll();
                sum1 += poll;
                sum2 -= poll;
                q1.add(poll);
            }
            tried++;
        }

        if(sum1!=sum2)
            tried = -1;

        return tried;
    }
}