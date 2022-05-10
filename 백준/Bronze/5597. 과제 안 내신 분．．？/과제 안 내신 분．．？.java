import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class Main {

    static int limit = 1000000000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> list = IntStream.rangeClosed(1, 30)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        for(int i=0;i<28;i++){
            Integer input = Integer.parseInt(br.readLine());
            list.remove(input);
        }

        list.sort((o1, o2) -> o1-o2);
        list.forEach(System.out::println);
    }
}

