import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    public Node root;

    public Tree(){

    }

    public Tree(Node root) {
        this.root = root;
    }


    //q1
    public Node buildTree(int in[], int level[]) {
        Node startNode = null;
        return constructTree(startNode, level, in, 0, in.length - 1);
    }

    Node constructTree(Node startNode, int[] levelOrder, int[] inOrder, int inStart, int inEnd) {

        if(inStart > inEnd) {
            return null;
        }
        if(inStart == inEnd){
            return new Node(inOrder[inStart]);
        }

        boolean found = false;
        int index = 0;

        for (int i = 0; i < levelOrder.length - 1; i++) {
            int data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++) {
                if (data == inOrder[j]) {
                    startNode = new Node(data);
                    index = j;
                    found = true;
                    break;
                }
            }
            if (found == true)
                break;
        }

        startNode.left =
                constructTree(startNode, levelOrder, inOrder, inStart, index - 1);
        startNode.right =
                constructTree(startNode, levelOrder, inOrder, index + 1, inEnd);

        return startNode;
    }



    //q2
    public boolean isFoldable() {
        return isFoldable(root);
    }
    private boolean isFoldable(Node node) {
        if(node == null) {
            return true;
        }
        mirror(node.right);
        boolean result = areIsomorphic(node.left, node.right);
        mirror(node.right);
        return result;
    }

    public void mirror() {
        mirror(root);
    }

    private void mirror(Node node) {
        if(node == null) {
            return;
        }
        mirror(node.left);
        mirror(node.right);
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    public boolean areIsomorphic(Node node1, Node node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null || node2 == null) {
            return false;
        }

        return areIsomorphic(node1.left, node2.left) && areIsomorphic(node1.right, node2.right);
    }





}//end of the class
