
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        ArrayList<LinkedList<Character>> list = new ArrayList<>();
        int maxLength = 0;

        for(int i=0;i<5;i++){

            String input = br.readLine();
            maxLength = Math.max(input.length(),maxLength);

            while (list.size() < maxLength){
                list.add(new LinkedList<>());
            }

            char[] charArray = input.toCharArray();

            for(int j=0;j<charArray.length;j++){
                list.get(j).add(charArray[j]);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (LinkedList<Character> linkedList : list) {
            for (Character ch : linkedList) {
                builder.append(ch);
            }
        }

        System.out.println(builder.toString());
    }
}

