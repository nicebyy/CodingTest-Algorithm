import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int n;
    static int target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        target = Integer.parseInt(input[1]);

        PriorityQueue<Country> countries = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        for(int i=0;i<n;i++){
            countries.add(new Country(br.readLine()));
        }

        int rank = 1;
        int cnt = 0;
        Country prev = countries.poll();
        while (!countries.isEmpty()){

            Country poll = countries.poll();

            if(prev.compareTo(poll) == 0){
                cnt++;
            }else{
                rank += cnt;
                cnt =0;
                rank++;
            }

            if(poll.key == target){
                break;
            }
            prev = poll;
        }

        System.out.println(rank);
    }

    static class Country implements Comparable<Country>{
        int gold;
        int silver;
        int bronze;
        int key;

        public Country(String in) {
            String[] split = in.split(" ");
            this.key = Integer.parseInt(split[0]);
            this.gold = Integer.parseInt(split[1]);
            this.silver = Integer.parseInt(split[2]);
            this.bronze = Integer.parseInt(split[3]);
        }

        @Override
        public int compareTo(Country o) {
            if (this.gold == o.gold) {
                if (this.silver == o.silver) {
                    return o.bronze - this.bronze;
                }
                return o.silver - this.silver;
            }
            return o.gold - this.gold;
        }
    }
}
