import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {



    }


    //Question. 1:  Given an integer array arr, move all 0's to the end of it while maintaining
    // the relative order of the non-zero elements.
    //Note that you must do this in-place without making a copy of the array.
    //Input: nums = [0,1,0,3,12]
    //Output: [1,3,12,0,0]

    //time O(n^2)   space (1)
    public void moveZero(int[] nums){
        int i = 0;
        int k = nums.length - 1;
        for(i = 0; i <= k; i++){
            if(nums[i] == 0){
                int temp = nums[i];
                int j;
                for(j = i + 1; j <= k; j++){
                    nums[j-1] = nums[j];
                }
                nums[k] = temp;
            }
            k--;
        }
    }

    //Question 2 : Given an arrays arr containing n distinct numbers in the range [0, n] ,
    //return the only number in the range that is missing from the array.
    //Input: nums = [3,0,1]
    //Output: 2

    //time O(n)   space  O(1)
    public int findMiss(int[] nums){
        Arrays.sort(nums);
        int i = 0;
        for(i = 0; i < nums.length; i++){
            if(nums[i] != i){
                return i;
            }
        }
        return i;
    }

    //Question 3: Given a cyclic link List get the middle of the link list.

    public ListNode getMiddle(ListNode head){
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        //find the intersection
        if(fast == slow){
            ListNode temp = head;
            if(slow.next == temp.next){
                slow.next = null;
            }else{
                temp = temp.next;
                slow = slow.next;
            }
        }else{
            slow = slow.next;
            fast = fast.next.next;
        }

        slow = head;
        fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }



}//end of the main


class ListNode{
    int val;
    ListNode next;

    public ListNode(ListNode node, int val){
        this.val = val;
        next = null;
    }
}

class Node <T>{
    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

//Question 4: Given a full binary tree print nodes of tree with increasing order of
//count  See the screen for explanation

class Tree <T>{
    public Node<T> root;

    public Tree(){
        this.root = null;
    }

    public void levelOrderNotAll(){
        if(root == null){
            return;
        }
        Queue< Node<T> > queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        int count = 1;

        while( queue.isEmpty() == false ){
            Node<T> node = queue.remove();
            if(node != null){
                System.out.print(node.data + ", ");
                count++;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }else{
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
        System.out.println();
    }


}





