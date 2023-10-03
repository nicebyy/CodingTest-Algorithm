import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int n;

    static PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) ->o1.day-o2.day);
    static PriorityQueue<Integer> pays = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            int input[] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            pq.add(new Lecture(input[0],input[1]));
        }

        while (!pq.isEmpty()){
            Lecture curLecture = pq.poll();
            pays.add(curLecture.pay);

            if(pays.size() > curLecture.day){
                pays.poll();
            }
        }

        pays.stream().reduce(Integer::sum)
                .ifPresentOrElse(
                        System.out::println,
                        ()-> System.out.println(0)
                );
    }

    static class Lecture{

        int pay;
        int day;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }
    }
}


/**
 * 8
 * 10 1
 * 100 1
 * 100 2
 * 50 2
 * 80 3
 * 300 4
 * 400 4
 * 500 4
 */