import java.util.*;
import java.io.*;

class Solution {


    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        LinkedList<String> list = new LinkedList<>();

        if(cacheSize==0)
            return cities.length*5;

        for (String city : cities) {
            city = city.toLowerCase();

            if(list.contains(city)){
                list.remove(city);
                answer++;
            }else{
                if(list.size()>=cacheSize){
                    list.remove(cacheSize-1);
                }
                answer+=5;
            }

            list.add(0,city);
        }

        return answer;
    }

}
