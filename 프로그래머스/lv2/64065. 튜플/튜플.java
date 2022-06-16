import java.lang.reflect.Array;
import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

class Solution {
    public int[] solution(String s) {

        HashMap<Integer, Integer> map = new HashMap<>();
        String[] splits = s.replace("{{", "").replace("}}", "")
                        .split("\\},\\{");

        for (String split : splits) {
            stream(split.split(","))
                    .mapToInt(Integer::parseInt)
                    .forEach(e->{
                        if(map.containsKey(e))
                            map.replace(e,map.get(e)+1);
                        else
                            map.put(e,1);
                    });
        }
        
        TreeMap<Integer,Integer> answer = new TreeMap<>((o1, o2) -> o2-o1);
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            answer.put(en.getValue(),en.getKey());
        }
        
        return answer.values().stream().mapToInt(i->i).toArray();
    }
}