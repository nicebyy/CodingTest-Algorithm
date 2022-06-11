import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

class Solution {
    public int[] solution(int N, int[] stages) {

        TreeMap<Integer, Long> map = stream(stages)
                .boxed()
                .collect(groupingBy(e -> e, TreeMap::new, counting()));

        TreeMap<Integer, Double> ans = new TreeMap<>();
        IntStream.rangeClosed(1,N)
                .forEach(e->ans.put(e,0.0));

        Double size = (double) stages.length;

        for (var en : map.entrySet()) {
            ans.replace(en.getKey(),(en.getValue()/size));
            size-=en.getValue();
        }

        List<Map.Entry<Integer, Double>> entries = new ArrayList<>(ans.entrySet());
        entries.sort((o1, o2) -> {
            if(o1.getValue()==o2.getValue())
                return o1.getKey()-o2.getKey();
            return o2.getValue().compareTo(o1.getValue());
        });
//
//        return entries.stream()
//                .map(Map.Entry::getKey)
//                .collect(toCollection(ArrayList::new))
//                .stream().mapToInt(i->i).toArray();

        return entries.stream()
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}