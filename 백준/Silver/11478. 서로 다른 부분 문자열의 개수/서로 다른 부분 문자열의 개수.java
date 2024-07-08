import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        HashSet<String> set = new HashSet<>();
        for(int i=0;i<word.length();i++){

            for(int j=i;j<=word.length();j++){
                String substring = word.substring(i, j);
                if(!substring.equals("")){
                    set.add(word.substring(i,j));
                }
            }
        }

        System.out.println(set.size());
    }
}
