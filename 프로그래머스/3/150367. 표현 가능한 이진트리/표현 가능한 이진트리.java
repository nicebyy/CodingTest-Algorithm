
import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        for (long number : numbers) {

            String treeString = createBinaryTree(number);
            if(checkValid(treeString)){
                result.add(1);
            }else{
                result.add(0);
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }

    private boolean checkValid(String treeString) {

        boolean valid = true;
        int mid = treeString.length()/2;
        char root = treeString.charAt(mid);
        String left = treeString.substring(0,mid);
        String right = treeString.substring(mid+1,treeString.length());

        if(root == '0'){

            if(getSubRoot(left)=='1' || getSubRoot(right) == '1'){
                return false;
            }
        }

        if(left.length()>=3)
            valid = checkValid(left) && checkValid(right);
        return valid;
    }

    public char getSubRoot(String target){
        return target.charAt((target.length())/2);
    }

    public String createBinaryTree(Long number){

        String convertedString = Long.toBinaryString(number);
        int n=2;

        while (n-1<convertedString.length()){
            n = n*2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(convertedString);
        for(int i=convertedString.length();i<n-1;i++){
            sb.insert(0,0);
        }
        return sb.toString();
    }
}