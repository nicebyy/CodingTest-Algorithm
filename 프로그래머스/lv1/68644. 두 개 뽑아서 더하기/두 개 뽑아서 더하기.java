
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;


class Solution {
    public int[] solution(int[] numbers) {

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                list.add(numbers[i]+numbers[j]);
            }
        }
        
        return list.stream()
                .distinct()
                .sorted()
                .mapToInt(i->i).toArray();

    }
}