public class Main {

    public static void main(String[] args) {
        Main t = new Main();
        ListNode h1a = genListHead(new int[]{4,1,8,4,5});
        ListNode h1b = genListHead(new int[]{5,6,1,8,4,5});
        ListNode h1 = h1a, h2 = h1b;
        h1 = h1.next; h2 = h2.next.next;
        h1.next = h2.next;
        //System.out.println(t.findIntersection(h1a, h1b));

        ListNode q2 = genListHead(new int[]{1,2,6,3,4,5,6});
        ListNode q21 = genListHead(new int[]{7,7});
        //System.out.println(t.removeValNode(q21, 7));

        ListNode q31 = genListHead(new int[]{9,9,9,9,9,9,9});
        ListNode q32 = genListHead(new int[]{9,9,9,9});
        //System.out.println(t.sumListNode(q31, q32).allNode());


        ListNode q41 = genListHead(new int[]{2,1,3,5,6,4,7});
        ListNode q42 = genListHead(new int[]{1,2,3,4});
        //System.out.println(t.reorderList(q42).allNode());

        ListNode q51 = genListHead(new int[]{1,2,3,4,5,6});
        ListNode q52 = genListHead(new int[]{1,2,3,4,5});
        System.out.println(t.findMid(q52).allNode());

    }

    //Question 1:
    //Given the heads of two singly linked-lists headA and headB, return the node at  which the two lists intersect. If the two linked lists have no intersection at all,  return null.
    //For example, the following two linked lists begin to intersect at node c1: The test cases are generated such that there are no cycles anywhere in the entire  linked structure.
    //Note that the linked lists must retain their original structure after the function  returns.
    //Example 1:
    //
    //Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3 Output: Intersected at '8'
    //Explanation: The intersected node's value is 8 (note that this must not be 0 if the  two lists intersect).
    //From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8, 4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes befor e the intersected node in B.

    //time o(n) space o(1)
    public ListNode findIntersection(ListNode head1, ListNode head2) {
        int len1 = getLength(head1);
        int len2 = getLength(head2);
        System.out.println(len1 + " " + len2);
        ListNode longer;
        ListNode shorter;
        if(len1 > len2) {
            longer = head1;
            shorter = head2;
        } else {
            longer = head2;
            shorter = head1;
        }

        for(int i = 0; i < Math.abs(len1 - len2); i++) {
            longer = longer.next;
        }

        ListNode res = null;
        while(longer != null && shorter != null) {
            if(longer == shorter && (longer.val != 0 && shorter.val != 0)) {
                res = longer;
                break;
            }
            longer = longer.next;
            shorter = shorter.next;
        }
        return res;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while(head != null) {
            head = head.next;
            len++;
        }
        return len;
    }


    //Question2 :
    //Given the head of a linked list and an integer val, remove all the nodes of the  linked list that has Node.val == val, and return the new head.
    //Input: head = [1,2,6,3,4,5,6], val = 6
    //Output: [1,2,3,4,5]
    //Example 2:
    //Input: head = [], val = 1
    //Output: []
    //Example 3:
    //Input: head = [7,7,7,7], val = 7
    //Output: []
    public ListNode removeValNode(ListNode head, int val) {
        if(head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode newHead = dummy;

        while(dummy != null && dummy.next != null) {
            if(dummy.next.val == val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return newHead.next;
    }




    //Question 3:
    //You are given two non-empty linked lists representing two non-negative integers.
    // The digits are stored in reverse order, and each of their nodes contains a single  digit. Add the two numbers and return the sum as a linked list.
    //You may assume the two numbers do not contain any leading zero, except the  number 0 itself.
    //Input: l1 = [2,4,3], l2 = [5,6,4]
    //Output: [7,0,8]
    //Explanation: 342 + 465 = 807.
    //Example 2:
    //Input: l1 = [0], l2 = [0]
    //Output: [0]
    //
    //
    //
    //Example 3:
    //Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    //Output: [8,9,9,9,0,0,0,1]

    public ListNode sumListNode(ListNode head1, ListNode head2) {
        ListNode res = new ListNode(0);
        ListNode head = res;
        int carry = 0;
        while(head1 != null || head2 != null) {
            int v1, v2;
            if(head1 == null) {
                v1 = 0;
            }else {
                v1 = head1.val;
            }

            if(head2 == null) {
                v2 = 0;
            }else {
                v2 = head2.val;
            }

            int v3 = v1 + v2 + carry;

            if(v3 > 9) {
                carry = 1;
                v3 = v3 - 10;
            } else {
                carry = 0;
            }
            if(head1 != null)head1 = head1.next;
            if(head2 != null)head2 = head2.next;
            res.next = new ListNode(v3);
            res = res.next;
        }
        if(carry > 0) {
            res.next = new ListNode(1);
        }

        return head.next;
    }


    //Question 4:
    //Given the head of a singly linked list, group all the nodes with odd indices
    // together followed by the nodes with even indices, and return the reordered list.
    //The first node is considered odd, and the second node is even, and so on.
    //Note that the relative order inside both the even and odd groups should remain  as it was in the input.
    //Example 1:
    //
    //Input: head = [1,2,3,4,5]
    //Output: [1,3,5,2,4]
    //Example 2:
    //Input: head = [2,1,3,5,6,4,7]
    //Output: [2,3,6,7,1,5,4]
    //Constraints:
    //• n == number of nodes in the linked list
    //• 0 <= n <= 104
    //• -106 <= Node.val <= 106
    public ListNode reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode evenhead = head.next;
        ListNode even = evenhead;
        ListNode odd = head;
        //1-2-3-4-5-6
        while(even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenhead;
        return head;
    }




    //Question 5: Given the head of a singly linked list, return the middle node of the  linked list.
    //If there are two middle nodes, return the second middle node.
    //Example 1:
    //Input: head = [1,2,3,4,5]
    //Output: [3,4,5]
    //Explanation: The middle node of the list is node 3.
    //Example 2:
    //Input: head = [1,2,3,4,5,6]
    //Output: [4,5,6]
    //Explanation: Since the list has two middle nodes with values 3 and 4, we return  the second one.
    //Constraints:
    //• The number of nodes in the list is in the range [1, 100].
    //• 1 <= Node.val <= 100
    public ListNode findMid(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        //1-2-3-4
        //1-2-3
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }



    //Question 6:
    //Given the head of a linked list, return the node where the cycle begins. If there is  no cycle, return null.
    //There is a cycle in a linked list if there is some node in the list that can be reached  again by continuously following the next pointer. Internally, pos is used to denote  the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if  there is no cycle. Note that pos is not passed as a parameter.
    //Do not modify the linked list.
    //Example 1:
    //Input: head = [3,2,0,-4], pos = 1
    //Output: tail connects to node index 1
    //Explanation: There is a cycle in the linked list, where tail connects to the second  node.
    //Example 2:
    //
    //Input: head = [1,2], pos = 0
    //Output: tail connects to node index 0
    //Explanation: There is a cycle in the linked list, where tail connects to the first  node.
    //Example 3:
    //
    //Input: head = [1], pos = -1
    //Output: no cycle
    //Explanation: There is no cycle in the linked list.
    //Constraints:
    //• The number of the nodes in the list is in the range [0, 104]. • -105 <= Node.val <= 105
    //• pos is -1 or a valid index in the linked-list.
    public ListNode findCycleStart(ListNode head) {
        // total length = a +b + c and b+c is cycle length
        // for slow is a+ b for fast is a+b+c+b and we have fast = 2*slow
        //so c == a
        ListNode fast = head;
        ListNode slow = head;
        ListNode inter = null;
        while(fast != null && fast.next != null) {

            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                inter = fast;
                break;
            }
        }

        if(inter == null) {
            return null;
        }
        ListNode res = head;
        while(res != slow) {
            res = res.next;
            slow = slow.next;
        }

        return res;
    }









    private static ListNode genListHead(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode p = head;
        for(int i = 1; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        return head;
    }

}

class ListNode{
    int val;
    ListNode next;
    ListNode(int v) {
        val = v;
    }
    @Override
    public String toString() {
        return "[" + val + "]";
    }

    public String allNode() {
        String r = this.toString();
        while(next != null) {
            r += next.toString();
            next = next.next;
        }
        return r;
    }


}
