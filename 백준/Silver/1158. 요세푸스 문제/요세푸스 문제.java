import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        LinkedList<Integer> q = new LinkedList<>();

        for(int i=1;i<=input[0];i++)
            q.add(i);

        int cnt=0;
        int curPos = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()){
            curPos = (curPos+input[1]-1)%q.size();
            ans.add(q.get(curPos));
            q.remove(curPos);
        }

        System.out.println(ans.toString().replaceAll("\\[","<").replaceAll("]",">"));
    }
}

