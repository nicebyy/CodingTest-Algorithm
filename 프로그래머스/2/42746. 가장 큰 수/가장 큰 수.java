
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        PriorityQueue<CustomNumber> pq = Arrays.stream(numbers)
                .mapToObj(CustomNumber::new)
                .collect(Collectors.toCollection(PriorityQueue::new));

        StringBuilder sb = new StringBuilder();
        
        if(pq.peek().prefix == 0)
            return "0";
        while (!pq.isEmpty()){
            sb.append(pq.poll().number);
        }

        return sb.toString();
    }

    static class CustomNumber implements Comparable<CustomNumber> {

        int prefix;
        String number;
        String[] numberSplit;

        public CustomNumber(int num) {
            this.number = Integer.toString(num);
            this.numberSplit = number.split("");
            this.prefix = Integer.parseInt(numberSplit[0]);
        }

        @Override
        public int compareTo(CustomNumber o) {

            if(o.prefix == this.prefix){
                return Integer.parseInt(o.number + this.number) - Integer.parseInt(this.number+o.number);
            }
            return o.prefix - this.prefix;
        }

    }
}