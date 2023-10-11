import java.util.*;
import java.util.Collections.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> clothesMap = Arrays.stream(clothes)
                .collect(
                        Collectors.groupingBy(c -> c[1],
                        Collectors.mapping(c -> c[0], Collectors.toList()))
                );
        clothesMap.values().forEach(map->map.add("NoWear"));

        return clothesMap.values().stream()
                .map(List::size)
                .reduce((total, size) -> total * size).get()-1;
    }
}