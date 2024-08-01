
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        char[] words = br.readLine().toCharArray();
        int queryCount = Integer.parseInt(br.readLine());

        int[][] sum = new int['z' - 'a' + 1][words.length];

        sum[words[0]-'a'][0] = 1;

        for (int i = 1; i < words.length; i++) {
            for(char word='a'; word<='z' ; word++){
                sum[word-'a'][i] =
                        words[i]==word?
                        sum[word-'a'][i-1]+1 : sum[word-'a'][i-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queryCount; i++) {
            String[] query = br.readLine().split(" ");
            char queryWord = query[0].charAt(0);
            int wordIndex = queryWord - 'a';

            int from = Integer.parseInt(query[1]);
            int to = Integer.parseInt(query[2]);

            int result = sum[wordIndex][to] - sum[wordIndex][from];
            if(words[from] == queryWord){
                result++;
            }

            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

}


/**
 *
 * seungjaehwang
 * 1
 * s 0 0
 *
 * seungja
 * 1
 * a 0 0
 *
 * seungjaehwang
 * 2
 * e 0 12
 * a 0 12
 *
 */

