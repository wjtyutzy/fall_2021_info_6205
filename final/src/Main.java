import java.util.*;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();

        //q1
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        int[] level = {1, 2, 3, 4, 5, 6, 7};
        Node node = tree.buildTree(inorder, level);
        System.out.println(node.data);

        //q2
        tree.root = createTree1();
        System.out.println(tree.isFoldable());


        //q3
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumpsToReachEnd(arr));

        //q4
        String[] words = {"hello","leetcode"};
        System.out.println(alienOrder(words));

    }//end of main


    private static Node createTree() {
        Node root = new Node(1);

        //second level
        root.left = new Node(2);
        root.right = new Node(3);

        //third level
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        return root;
    }

    private static Node createTree1() {
        Node root = new Node(8);

        root.left = new Node(7);
        root.right = new Node(10);

        root.left.right = new Node(9);
        root.right.left = new Node(11);

        return root;
    }


    //q3
    public static int minJumpsToReachEnd(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        if(arr.length == 1) {
            return 1;
        }

        int[] jumps = new int[arr.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;

        int[] indexes = new int[arr.length];
        Arrays.fill(indexes,-1);
        indexes[0] = 0;

        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] + i >= j) {
                    if(jumps[j] > jumps[i] + 1) {
                        jumps[j] = jumps[i] + 1;
                        indexes[j] = i;
                    }
                }
            }
        }

        if(jumps[arr.length -1] == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        int minJumps = jumps[arr.length - 1];
        int maxIndex = arr.length - 1;
        Stack<Integer> stack = new Stack();
        for(int i = 0; i < minJumps; i++) {
            stack.push(arr[maxIndex]);
            maxIndex = indexes[maxIndex];
        }
        stack.push(arr[0]);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
        }
        System.out.println();
        return minJumps;
    }




    //q4
    public static boolean alienOrder(String[] words) {
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        int count = 0;
        for(int i = 0; i < words.length; i++) {
            String cur = words[i];
            for(int j = 0; j < cur.length(); j++) {
                char c1 = cur.charAt(j);
                if(indegree[c1 - 'a'] == -1) {
                    count++;
                    indegree[c1 - 'a'] = 0;
                }
            }
        }

        Map<Character, Set<Character>> map = new HashMap<>();
        buildMap(map, words, indegree);

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        //System.out.println(queue.size());
        StringBuilder res = new StringBuilder();
        while(!queue.isEmpty()) {
            char node = (char)(queue.poll() + 'a');
            res.append(node);
            Set<Character> curset = map.get(node);
            if(curset != null) {
                for(char c : curset) {
                    indegree[c - 'a']--;
                    if(indegree[c - 'a'] == 0) {
                        queue.offer(c - 'a');
                    }
                }
            }
        }

        if(res.length() != count){
            return false;
        }
        return true;
    }


    private static void buildMap(Map<Character, Set<Character>> map, String[] words, int[] indegree) {
        for(int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int len = Math.min(cur.length(), next.length());
            for(int j = 0; j < len; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if(c1 != c2) {
                    // System.out.println(c1 + "  " + c2);
                    if(!map.containsKey(c1)) {
                        map.put(c1, new HashSet<>());
                    }

                    boolean flag = map.get(c1).add(c2);//means if there already have the relation before
                    // we cannot increase the indegree for this character
                    // System.out.println(c1 + "  " + c2 +"   " + flag);
                    indegree[c2 - 'a'] += flag ? 1 : 0;
                    break;
                }
            }
        }
    }



}//end of class
