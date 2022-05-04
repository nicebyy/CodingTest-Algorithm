import java.io.*;
import java.util.*;
 
public class Main {
    public static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinaryTree tree = new BinaryTree(Integer.parseInt(br.readLine()));
        String s = "";
        while ((s = br.readLine()) != null && s.length() != 0) {    //EOF까지 입력받음
            tree = tree.addTree(tree, Integer.parseInt(s));
        }
        postorder(tree);
        System.out.println(sb);
    }
 
    public static void postorder(BinaryTree tree) {
        if (tree.left != null) postorder(tree.left);
        if (tree.right != null) postorder(tree.right);
        sb.append(tree.data + "\n");
    }
}
 
class BinaryTree {
    int data;
    BinaryTree left;
    BinaryTree right;
 
    public BinaryTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
 
    public BinaryTree addTree(BinaryTree tree, int val) {
        BinaryTree curTree = null;
        if (tree == null) return new BinaryTree(val);
        if (tree.data > val) {
            curTree = addTree(tree.left, val);  //새로운 하위 이진 트리 생성
            tree.left = curTree;                //부모 노드의 왼쪽에 붙인다
        } else if (tree.data < val) {
            curTree = addTree(tree.right, val); //새로운 하위 이진 트리 생성
            tree.right = curTree;               //부모 노드의 오른쪽에 붙인다
        }
        return tree;                            //새로 붙인 트리 return
    }
}