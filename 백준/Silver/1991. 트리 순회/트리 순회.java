import java.io.*;

class Node{
	String data;
	Node left;
	Node right;
	public Node(String data) {
		this.data=data;
	}
}
class Tree{
	public Node root;
	
	public void add(String data,String left,String right) {
		if(root==null) {
			if(!data.equals("."))
				root = new Node(data);
			if(!left.equals("."))
				root.left = new Node(left);
			if(!right.equals("."))
				root.right = new Node(right);
		}
		else
			search(root,data,left,right);
	}
	public void search(Node root,String data,String left,String right) {
		if(root==null)
			return;
		else if(root.data.equals(data)) {
			if(!left.equals("."))
				root.left = new Node(left);
			if(!right.equals("."))
				root.right=new Node(right);
		}
		else
		{
			search(root.left,data,left,right);
			search(root.right,data,left,right);
		}
	}
	public void setRoot(Node node) {
		this.root=root;
	}
	public Node getRoot() {
		return this.root;
	}
	public void inOrder(Node node) {
		if(node.left!=null) 
			inOrder(node.left);
		System.out.print(node.data);
		if(node.right!=null)
			inOrder(node.right);
	}
	public void preOrder(Node node) {
		System.out.print(node.data);
		if(node.left!=null)
			preOrder(node.left);
		if(node.right!=null)
			preOrder(node.right);
	}
	public void postOrder(Node node) {
		if(node.left!=null)
			postOrder(node.left);
		if(node.right!=null)
			postOrder(node.right);
		System.out.print(node.data);
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[cnt];
		Tree t = new Tree();
		for(int i=0;i<cnt;i++)
		{
			String[] input = br.readLine().split(" ");
			t.add(input[0], input[1], input[2]);
		}
		t.preOrder(t.getRoot());
		System.out.println("");
		t.inOrder(t.getRoot());
		System.out.println("");
		t.postOrder(t.getRoot());
	}

}
