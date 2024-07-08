

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] word = br.readLine().toCharArray();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<word.length;i++){

            String curWord = "";
            for(int j=i;j<word.length;j++){
                curWord+= word[j];
                if(!curWord.equals("")){
                    set.add(curWord);
                }
            }
        }

        System.out.println(set.size());
    }
}
