
import java.util.*;
import java.io.*;

class Solution {

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        LinkedList<Integer>[] list = new LinkedList[board.length];
        Stack<Integer> basket = new Stack<>();

        for(int i=0;i<board.length;i++)
            list[i] = new LinkedList<Integer>();

        for (int[] doll : board) {
            for(int i=0;i<board.length;i++){
                if(doll[i]!=0)
                    list[i].add(doll[i]);
            }
        }

        for (int move : moves) {

            if(list[move-1].isEmpty())
                continue;
            int poll = list[move-1].poll();

            System.out.println(move-1+"번째 자리에서 "+poll+"을 뽑음");

            if(basket.isEmpty()) {
                basket.push(poll);
                continue;
            }

            int peek = basket.peek();

            if (peek == poll) {
                basket.pop();
                answer += 2;
                System.out.println("펑");
            } else {
                basket.push(poll);
            }

        }

        return answer;
    }
}

