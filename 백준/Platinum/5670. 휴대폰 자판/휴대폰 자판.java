
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int answer;

    public static void main(String[] args) throws IOException {

        String input;

        while ((input = br.readLine()) != null) {
            n = Integer.parseInt(input);

            Node root = new Node();
            answer = 0;
            List<char[]> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] charArray = br.readLine().toCharArray();
                root.addWord(charArray, 0);
                list.add(charArray);
            }
            for (char[] word : list) {
                answer+=root.calculate(word);
            }
            System.out.printf("%.2f%n", (double) (answer) / n);
        }
    }

    static class Node {

        HashMap<Character, Node> map;
        boolean isEnd;

        public Node() {
            this.map = new HashMap<>();
            this.isEnd = false;
        }

        public void addWord(char[] wordArr, int index) {

            if (index == wordArr.length) {
                return;
            }

            char targetCharacter = wordArr[index];

            if (!this.map.containsKey(targetCharacter)) {
                this.map.put(targetCharacter, new Node());
            }
            Node next = this.map.get(targetCharacter);
            if (index == wordArr.length - 1) {
                next.isEnd = true;
            }
            next.addWord(wordArr, index + 1);
        }

        public int calculate(char[] word) {

            int sum = 1;
            Node node = this;

            for(int i=0; i<word.length; i++){
                node = node.map.get(word[i]);

                if(i < word.length-1 && (node.isEnd || node.map.size()>1)){
                    sum++;
                }
            }
            return sum;
        }
    }
}

/**
 * 4
 * hello
 * hell
 * heaven
 * goodbye
 * 3
 * hi
 * he
 * h
 * 7
 * structure
 * structures
 * ride
 * riders
 * stress
 * solstice
 * ridiculous
 * 2
 * bbbb
 * bbaba
 */