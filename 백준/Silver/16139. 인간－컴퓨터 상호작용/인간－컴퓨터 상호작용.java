
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

        for (int i = 0; i < words.length; i++) {
            sum[words[i] - 'a'][i]++;
        }

        for(char word='a'; word<='z' ; word++){
            int wordIndex = word-'a';

            for(int i=1;i<words.length;i++){
                sum[wordIndex][i] += sum[wordIndex][i-1];
            }
        }

        for (int i = 0; i < queryCount; i++) {
            String[] query = br.readLine().split(" ");
            char queryWord = query[0].charAt(0);

            int from = Integer.parseInt(query[1]);
            int to = Integer.parseInt(query[2]);

            int result = sum[queryWord - 'a'][to] - sum[queryWord - 'a'][from];
            if(words[from] == queryWord){
                result++;
            }

            System.out.println(result);
        }
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

