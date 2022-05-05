import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int operandCount = Integer.parseInt(br.readLine());
        HashMap<Character, Double> operands = new HashMap<>();
        Stack<Double> stack = new Stack<>();

        char[] equations = br.readLine().toCharArray();

        for(int i=0;i<operandCount;i++){
            double value = Double.parseDouble(br.readLine());
            operands.put((char) ('A'+i),value);
        } // end input

        for (char ch : equations) {

            if(!isOperator(ch)){
                stack.push(operands.get(ch));
            }else {

                Double op1 = stack.pop();
                Double op2 = stack.pop();
                
                Double result = switch (ch) {
                    case '+' -> op2 + op1;
                    case '-' -> op2 - op1;
                    case '/' -> op2 / op1;
                    case '*' -> op2 * op1;
                    default -> 0.00;
                };
                stack.push(result);
            }
        }
        System.out.printf("%.2f",stack.pop());
    }

    static boolean isOperator(char ch){
        return ch<'A'||ch>'Z';
    }
}