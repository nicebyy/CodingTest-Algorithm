import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][];

        for(int i=0;i<n;i++)
            arr[i] = br.readLine().toCharArray();
        // end input

        StringBuilder ans = new StringBuilder();

        for(int i=0;i<arr[0].length;i++){
            char peek = arr[0][i]; // 암거나 하나 대표문자로 선언

            for(int j=0;j<n;j++){
                if(peek!=arr[j][i]){ // 세로로 비교했을 때 다르면
                    peek = '?'; // peek 을 '?' 으로 치환
                    break;
                }
            }
            ans.append(peek);
        }
        System.out.println(ans.toString());
    }
}

