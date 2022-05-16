import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    static int n;
    static ArrayList<String> books;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        while (tc-->0){

            n = Integer.parseInt(br.readLine());
            books = new ArrayList<>();

            for(int i=0;i<n;i++)
                books.add(br.readLine()); // end input
            // 길이 순으로 정렬
            Collections.sort(books);

            String ans="NO";
            if(isConsistency())
                ans = "YES";

            System.out.println(ans);
        }
    }

    private static boolean isConsistency() {

        for (int i = 0; i < n-1; i++) {
            if (books.get(i + 1).startsWith(books.get(i)))
                return false;
        }
        return true;
    }


}
