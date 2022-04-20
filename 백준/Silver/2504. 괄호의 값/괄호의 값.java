import static java.util.Arrays.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * ( () [ [] ] ) ( [] )
 *
 * stack :
 * result : (2+3*3)*2+
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> words = stream(br.readLine().split(""))
                .collect(Collectors.toCollection(ArrayList::new));

        int answer = 0;
        Stack<String> stack = new Stack<>();
        String prev = "";
        int cur = 1; // 1에서 부터 시작

        for (String word : words) {
            
            if(word.equals("(")){  // 열리는 괄호 일때 
                cur *=2;
                stack.push(word);
            }else if(word.equals("[")){
                cur *= 3;
                stack.push(word);
            }else if(word.equals(")")){ // 닫히는 괄호 일때

                if(stack.isEmpty() || !stack.peek().equals("(")){ // 잘못 된 괄호이면 break
                    answer=0;
                    break;
                }
                else if(prev.equals("(")) // 중간 정산 
                    answer += cur;

                stack.pop();
                cur /= 2;
            }else if(word.equals("]")){

                if(stack.isEmpty() || !stack.peek().equals("[")){
                    answer=0;
                    break;
                }
                else if(prev.equals("["))
                    answer += cur;

                stack.pop();
                cur /= 3;
            }
            prev = word; // 중간 정산을 위한 이전 값 기록
        }

        if(!stack.isEmpty()) // 괄호짝이 남는 경우 => 잘못된 괄호
            answer = 0;
        System.out.println(answer);
    }
}

