import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import static java.util.Arrays.stream;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashSet<String> nameSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int colorCount = input[0];
        int nickCount = input[1];

        ColorNode rootNode = new ColorNode();
        for(int i=0; i<colorCount; i++){
            rootNode.insert(br.readLine().toCharArray(), 0);
        }
        for(int i=0; i<nickCount; i++){
            nameSet.add(br.readLine());
        }

        int queryCount = Integer.parseInt(br.readLine());

        StringBuilder answers = new StringBuilder();
        for(int i=0; i<queryCount; i++){
            String name = br.readLine();
            rootNode.find(name.toCharArray(), 0, answers);
        }
        System.out.println(answers);
    }

    static class ColorNode{

        ColorNode[] colorNodes;
        boolean isEnd;

        public ColorNode() {
            this.colorNodes = new ColorNode[26];
            this.isEnd = false;
        }

        public void setEnd(){
            this.isEnd = true;
        }

        public boolean hasNotChild(char key){
            return this.colorNodes[key-'a'] == null;
        }

        public ColorNode next(char key){
            return this.colorNodes[key-'a'];
        }

        public void insert(char[] word, int index){

            if(index == word.length){
                return;
            }

            if(hasNotChild(word[index])){
                this.colorNodes[word[index]-'a'] = new ColorNode();
            }
            this.colorNodes[word[index]-'a'].insert(word,index+1);

            if(index == word.length-1){
                this.colorNodes[word[index]-'a'].setEnd();
            }
        }

        public void find(char[] word, int index, StringBuilder answers){

            if(word.length == index || hasNotChild(word[index])){
                answers.append("No\n");
                return;
            }

            ColorNode nextNode = this.next(word[index]);

            if(nextNode.isEnd){

                String name = String.valueOf(word).substring(index + 1, word.length);

                if(nameSet.contains(name)){
                    answers.append("Yes\n");
                    return;
                }
            }

            nextNode.find(word, index+1, answers);
        }
    }
}
