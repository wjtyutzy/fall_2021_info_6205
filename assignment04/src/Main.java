import java.util.Stack;

public class Main {

    public static void main(String[] args) {


    }

    //Q1. Write a function to delete a node in a singly-linked list. You will not be given access to  the head of the list, instead you will be given access to the node to be deleted directly.

    //time o(1) space o(1)
    public void delNode(ListNode del) {
        //1-2-3
        del.val = del.next.val;
        del.next = del.next.next;
    }



    //Q2. Given a Circular Linked List node, which is sorted in ascending order, write a function to insert a  value insertVal into the list such that it remains a sorted circular list. The given node can be a  reference to any single node in the list and may not necessarily be the smallest value in the circular  list.


    //time o(n) space o(1)
    public Node insert(Node head, int val) {
        if(head == null ) {
            Node tmp = new Node(val);
            tmp.next =  tmp;
            return tmp;
        }

        Node max = head;
        while(max.val <= max.next.val && max.next != head) {
            max = max.next;
        }

        Node min = max.next;

        if(val <= min.val || val >= max.val) {
            //same situation insert
            max.next = new Node(val, min);
            return head;
        }

        Node tmp = min;

        while(tmp.next.val < val) {
            tmp = tmp.next;
        }
        tmp.next = new Node(val, tmp.next);

        return head;
    }



    //Q3. Design your implementation of the circular double-ended queue (deque). Implement the MyCircularDeque class:


    class MyCircularDeque {
        int size;
        int count;
        Dll head;
        Dll tail;
        //time o(1) space o(1)
        public MyCircularDeque(int size) {
            this.size = size;
            count = 0;
            head = new Dll(-1);
            tail = new Dll(-1);
            link(head, tail);
        }
        //time o(1) space o(1)
        public boolean insertFront(int val){
            if(this.isFull()) {
                return false;
            }
            count++;
            new Dll(val, head, head.next);
            return true;
        }
        //time o(1) space o(1)
        public boolean insertLast(int val){
            if(this.isFull()) {
                return false;
            }
            count++;
            new Dll(val, tail.prev, tail);
            return true;
        }
        //time o(1) space o(1)
        public boolean deleteFront(){
            if(this.isEmpty()) {
                return false;
            }
            count--;
            link(head, head.next.next);
            return true;
        }
        //time o(1) space o(1)
        public boolean deleteLast(){
            if(this.isEmpty()) {
                return false;
            }
            count--;
            link(tail.prev.prev, tail);
            return true;
        }
        //time o(1) space o(1)
        public int getFront(){
            if(this.isEmpty()) {
                return -1;
            }
            return head.next.val;
        }
        //time o(1) space o(1)
        public int getRear(){
            if(this.isEmpty()) {
                return -1;
            }
            return tail.prev.val;
        }
        //time o(1) space o(1)
        public boolean isEmpty(){
            return count <= 0;
        }
        //time o(1) space o(1)
        public boolean isFull(){
            return count >= size;
        }

        //time o(1) space o(1)
        private void link(Dll a, Dll b){
            a.next = b;
            b.prev = a;
        }
    }

    class Dll{
        int val;
        Dll prev;
        Dll next;
        public Dll(int val){
            this.val = val;
        }
        public Dll(int val, Dll prev, Dll next) {
            this.val = val;
            this.prev = prev;
            prev.next = this;
            this.next = next;
            next.prev = this;
        }
    }





    //Q4

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head  == null || head.next == null) {
            return head;
        }
        int count = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while(count < m) {
            prev = prev.next;
            head = head.next;
            count++;
        }
        //prev.next = reverse_head
        int step = n - m;


        //dummy.next = head;
        while(head != null && head.next != null && step > 0) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = prev.next;
            prev.next = next;

            step--;
        }

        return dummy.next;
    }



    //Q5. Given head which is a reference node to a singly-linked list.Return the decimal value of the number in the linked list.


    //time o(n)/o(1) space o(n)/o(1) since node number max is 30,  space o(n)
    public int transNode(Node head){
        Stack<Integer> stack = new Stack<>();
        while(head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int res = 0;
        int i = 0;
        // 101
        // 2 ^2  + 0*2^1 + 1* 2^0
        while(!stack.isEmpty()) {
            if(stack.peek() == 0) {
                i++;
                stack.pop();
                continue;
            }
            stack.pop();
            res += 1 << i;
            i++;
        }
        return res;
    }


    //Q6. Design a queue that supports push and pop operations in the front, middle, and back. Implement the FrontMiddleBack class:


    class FrontMiddleBackQueue {

        int count;
        Dll mid;
        Dll head;
        Dll tail;

        public FrontMiddleBackQueue() {
            count = 0;
            head = new Dll(-1);
            tail = new Dll(-2);
            mid = null;
            link(head, tail);
        }

        public void pushFront(int val) {
            new Dll(val, head, head.next);
            count++;
            if(count == 1) {
                mid = head.next;
            } else {
                if(count % 2  == 0) {
                    move(-1);
                }
            }
        }

        public void pushMiddle(int val) {
            // 1 3 4 ---  1 2 3 4  mid is 2
            // 1 3 4 5 ---  1 2 3 4 5  mid is 3
            System.out.println(val +  "  pushMiddle");
            if(count == 0) {
                new Dll(val, head, head.next);
                mid = head.next;
            } else {

                if(count % 2 == 0) {
                    mid =  new Dll(val, mid, mid.next);
                } else {
                    mid = new Dll(val, mid.prev, mid);
                }
            }
            System.out.println(mid.val +  "  pushMiddle");
            count++;
        }

        public void pushBack(int val) {
            Dll end = new Dll(val, tail.prev, tail);
            count++;
            if(count == 1) {
                mid = end;
                //S//ystem.out.println(mid.val +  "  pushFront");
                return;
            }

            if(count % 2 != 0) {
                mid = mid.next;
            }
        }

        public int popFront() {
            //1 2 3 4 --- 2 3 4 >>
            // 1 2 3 -- -2 3 no
            if(isEmpty()) {
                return -1;
            }
            int res = head.next.val;
            link(head, head.next.next);
            count--;
            if(count % 2 != 0) {
                move(1);
            }
            return res;
        }

        public int popMiddle() {
            if(isEmpty()) {
                return -1;
            }
            System.out.println(mid.val +  "  popMiddle");
            int res = mid.val;

            link(mid.prev, mid.next);
            if(count % 2 == 0) {
                move(1);
            } else {
                move(-1);
            }
            count--;
            return res;
        }

        public int popBack() {
            if(isEmpty()) {
                return -1;
            }
            count--;

            if(count % 2 == 0) {
                move(-1);
            }
            Dll res = tail.prev;
            link(tail.prev.prev, tail);
            return res.val;
        }

        private void move(int step) {
            if(step > 0) {
                mid = mid.next;
            } else {
                mid = mid.prev;
            }
        }

        private void remove(Dll a){
            Dll prev = a.prev;
            Dll next = a.next;
            link(prev, next);
        }

        private boolean isEmpty(){
            return count <= 0;
        }

        private void link(Dll a, Dll b){
            a.next = b;
            b.prev = a;
        }
    }




    //Q7. A polynomial linked list is a special type of linked list where every node represents a term in a  polynomial expression.


    //time o(n) space o(1)
    public PolyNode addPoly(PolyNode a, PolyNode b) {
        if(a == null || b == null) {
            return a == null ? b : a;
        }
        PolyNode dummya = a;
        PolyNode dummyb = b;

        PolyNode dummy = new PolyNode();
        PolyNode res = dummy;

        while(dummya != null || dummyb != null) {
            PolyNode tmp = new PolyNode();
            if(dummya == null || dummyb == null) {
                PolyNode cur = dummya == null ? dummyb : dummya;
                tmp = new PolyNode(cur.coefficient, cur.power);

                if(dummya == null) {
                    dummyb = dummyb.next;
                } else {
                    dummya = dummya.next;
                }

            } else {
                if(dummya.power > dummyb.power) {
                    tmp = new PolyNode(dummya.coefficient, dummya.power);
                    dummya = dummya.next;
                } else if(dummyb.power > dummya.power) {
                    tmp = new PolyNode(dummyb.coefficient, dummyb.power);
                    dummyb = dummyb.next;
                } else {
                    tmp = new PolyNode(dummya.coefficient + dummyb.coefficient, dummyb.power);
                    dummya = dummya.next;
                    dummyb = dummyb.next;
                }
            }

            if(tmp.coefficient != 0) {
                dummy.next = tmp;
                dummy = dummy.next;
            }

        }
        return res.next;
    }

    /*
     * strictly descending order by its power value.
     * we need to find out the same pow and add them
     * which head power is bigger?
     *  [[2,2],[4,1],[3,0]],
     *  [[3,2],[-4,1],[-1,0]]
     * */


    class PolyNode {
        int coefficient, power;
        PolyNode next;
        public PolyNode() {

        }
        public PolyNode(int c, int p){
            coefficient = c;
            power = p;
        }

    }

    class ListNode{
        int val;
        ListNode next;
        ListNode(int v){
            val = v;
        }
        ListNode(int v, ListNode n) {
            val = v;
            next = n;
        }
    }



    class Node{
        int val;
        Node next;
        Node(int v){
            val = v;
        }
        Node(int v, Node n) {
            val = v;
            next = n;
        }
    }
}
