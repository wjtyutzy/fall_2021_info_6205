import java.util.*;

public class Main {

    public static void main(String[] args) {


    }

    //q1
    public void Trie() {
        root = new NodeT();
    }
    NodeT root;
    /** Inserts a word into the trie. */
    //time o(l) space o(l)
    public void insert(String word) {
        NodeT tmp = root;
        for(int i = 0; i< word.length(); i++){
            char c = word.charAt(i);
            int idx = c -'a';
            if(tmp.kids[idx] == null){
                tmp.kids[idx] = new NodeT();
            }
            tmp = tmp.kids[idx];
        }
        tmp.isWord = true;
    }

    //time o(l) space o(1)
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        NodeT tmp = root;
        for(int i = 0; i< word.length(); i++){
            char c = word.charAt(i);
            int idx = c -'a';
            if(tmp.kids[idx] == null){
                return false;
            }
            tmp = tmp.kids[idx];
        }
        return tmp.isWord;
    }

    //time o(l) space o(1)
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        NodeT tmp = root;
        for(int i = 0; i< prefix.length(); i++){
            char c = prefix.charAt(i);
            int idx = c -'a';
            if(tmp.kids[idx] == null){
                return false;
            }
            tmp = tmp.kids[idx];
        }
        return true;
    }

    class NodeT{
        boolean isWord;
        // char letter;
        NodeT[] kids = new NodeT[26];
        NodeT(){
            this.isWord = false;
        }
    }


    //q2
    //time o(v+e)  space o(v+e)
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        //build map ..
        if(start == end) {
            return true;
        }
        for(int[] e : edges) {
            int st = e[0];
            int ed = e[1];
            if(!map.containsKey(st)) {
                map.put(st , new HashSet<>());
            }
            map.get(st).add(ed);
            if(!map.containsKey(ed)) {
                map.put(ed , new HashSet<>());
            }
            map.get(ed).add(st);
        }

        Set<Integer> used = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        used.add(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            if(!map.containsKey(cur)) {
                return false;
            }
            for(int i : map.get(cur)) {
                if(i == end) {
                    return true;
                } else if(used.add(i)) {
                    queue.offer(i);
                }
            }
        }

        return false;
    }


    //q3
    //time o(v+e)  space o(v+e)
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    res++;
                    expend(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private void expend(char[][] grid, boolean[][] visited, int idxrow, int idxcol){
        int row = idxrow;
        int col = idxcol;
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ){
            return;
        }else if( !visited[row][col] && grid[row][col] == '1'){
            visited[row][col] = true;
            expend(grid, visited, row + 1, col);
            expend(grid, visited, row , col + 1);
            expend(grid, visited, row - 1, col);
            expend(grid, visited, row , col - 1);
        }
    }


    //q4
    //time o(v+e)  space o(v+e)
    public int findCircleNum(int[][] grid) {
        int n = grid.length;
        UF uf = new UF(n);


        for(int i = 0;  i < n; i++) {
            for(int j = 0 ; j < n; j++) {
                if(j == i) {
                    continue;
                }
                if(grid[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.count;
    }


    class UF {
        int[] roots;
        int count;
        UF(int n){
            roots = new int[n];
            count = n;
            for(int i = 0; i < n; i++) {
                roots[i] = i;
            }
        }

        int find(int a) {
            if(roots[a] != a) {
                roots[a] = find(roots[a]);
            }
            return roots[a];
        }

        void union(int a, int b){
            int ra = find(a);
            int rb = find(b);
            if(ra != rb) {
                roots[ra] = roots[rb];
                count--;
            }
        }
    }



    //q5
    //time o(v+e)  space o(v+e)
    public int countComponents(int n, int[][] edges) {
        roots = new int[n];
        Arrays.fill(roots, -1);

        int count = n;
        for(int[] e: edges){
            int a = e[0];
            int b = e[1];
            if(union(a, b)){
                count--;
            }
        }
        return count;
    }

    int[] roots;
    private int find( int a) {
        int tmp = a;
        if(roots[a] != -1) {
            int root = find(roots[a]);
            roots[a] = root;
            return root;
        }
        return a;
    }

    private boolean union(int a , int b) {
        int aa = find(a);
        int bb = find(b);
        if(aa != bb){
            roots[bb] = aa;
            return true;
        }
        return false;
    }



    //q6
    //time o(m*n)  space o(m*n)
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[n][n];

        Queue<Node6> queue = new LinkedList<>();
        //init
        queue.offer(new Node6(0, 0, 0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Node6 cur = queue.poll();
            if(cur.x == n - 1 && cur.y == n - 1) {
                return cur.step + 1;
            }
            for(int[] d : dirs) {
                int nx = d[0] + cur.x;
                int ny = d[1] + cur.y;

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Node6(nx, ny, cur.step + 1));
                }
            }
        }
        return -1;
    }

    class Node6 {
        int step;
        int x, y;
        Node6(int x, int y, int s){
            this. x = x; this. y = y;
            step = s;
        }
    }
    int[][] dirs = {{1, 0},{-1, 0},{0, 1},{0,-1},{1, 1},{-1, -1},{1, -1},{-1, 1}};



    //q7
    //time o(m*n)  space o(m*n)
    public int getDay(int[][] grid) {
        // bfs
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used = new boolean[m][n];
        Queue<Node> queue = new LinkedList<>();

        // init
        int good = 0;
        int bad = 0;
        for (int i = 0; i < m; i++) {
            for (int j  = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    good++;
                } else if(grid[i][j] == 2){
                    queue.offer(new Node(i, j, 0));
                    used[i][j] = true;
                }
            }
        }

        if(good == 0) {
            return 0;
        }

        // dirs
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] d : dirs) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !used[nx][ny] && grid[nx][ny] == 1) {
                    used[nx][ny] = true;
                    //grid[][] = 2;
                    good--;
                    if(good == 0){
                        return cur.val + 1;
                    }
                    queue.offer(new Node(nx, ny, cur.val + 1));
                }
            }
        }
        return -1;
    }

    class Node{
        int x, y;
        int val;
        Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.val = v;
        }
    }


}
