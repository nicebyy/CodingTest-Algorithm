
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] solution(int[][] nodeinfo) {

        ArrayList<Node> nodeList = new ArrayList<>();

        for(int i=0; i<nodeinfo.length;i++){

            int[] node = nodeinfo[i];
            nodeList.add(new Node(i+1,node));
        }

        nodeList.sort((node1, node2) -> node2.data.y - node1.data.y);

        Node root = nodeList.get(0);
        nodeList.remove(0);

        for (Node node : nodeList) {
            root.insertNode(node);
        }

        ArrayList<Integer> postOrderList = new ArrayList<>();
        Node.printPostOrder(root,postOrderList);

        ArrayList<Integer> preOrderList = new ArrayList<>();
        Node.printPreOrder(root,preOrderList);

        return new int[][]{
                preOrderList.stream().mapToInt(i->i).toArray(),
                postOrderList.stream().mapToInt(i->i).toArray()
        };
    }

    static class Node{

        int node;
        Point data;

        Node left;
        Node right;

        public Node() {
        }

        public Node(int index, int[] point){
            this.data = new Point(point[0], point[1]);
            this.node = index;
            this.left = null;
            this.right = null;
        }

        public void insertNode(Node target){

            if (target.data.x < this.data.x) { // 왼쪽 서브트리 삽입

                if (this.left == null){
                    this.left = target;
                }else{
                    this.left.insertNode(target);
                }
            }else{ // 오른쪽 서브트리 삽입

                if(this.right == null){
                    this.right = target;
                }else{
                    this.right.insertNode(target);
                }
            }

        }

        public static void printPostOrder(Node node, List<Integer> list){

            if(node.left != null){
                printPostOrder(node.left,list);
            }

            if(node.right != null){
                printPostOrder(node.right,list);
            }
            list.add(node.node);
        }

        public static void printPreOrder(Node node, List<Integer> list){

            list.add(node.node);

            if(node.left != null){
                printPreOrder(node.left,list);
            }

            if(node.right != null){
                printPreOrder(node.right,list);
            }

        }

    }

    static class Point{
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
