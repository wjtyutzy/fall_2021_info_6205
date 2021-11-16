import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    //q1
    //time o((mn)^2) space o(n)
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0 || board == null || board.length == 0|| board[0].length == 0) {
            return false;
        }
        int first = word.charAt(0);
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == first) {
                    visited[i][j] = true;
                    if( recur(board, i, j, 1, word, visited)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean recur(char[][] board, int x, int y, int idx, String word, boolean[][] visited) {
        if(idx == word.length()) {
            return true;
        }

        for(int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(nx >= 0 && nx < board.length && ny >=0 && ny < board[0].length) {
                if(!visited[nx][ny] && board[nx][ny] == word.charAt(idx)) {
                    visited[nx][ny] = true;

                    if( recur(board, nx, ny, idx + 1, word, visited ) ) {
                        return true;
                    }
                    visited[nx][ny] = false;
                }
            }
        }
        return false;
    }




    //q2
    public int countArrangement(int n) {
        count = 0;
        recur(new boolean[n + 1], 1);
        return count;
    }
    private int count;
    private void recur(boolean[] used, int lvl) {
        if(lvl == used.length){
            count++;
            return;
        }

        for(int i = 1; i < used.length; i++) {
            if(!used[i] && isD(lvl, i)) {
                used[i] = true;
                recur(used, lvl + 1);
                used[i] = false;
            }
        }

    }

    private boolean isD(int a, int b){
        if (a % b == 0 || b % a == 0) {
            return true;
        }
        return false;
    }




    //q3
    //time o(3^n) spce o(n)
    public List<String> restoreIpAddresses(String s) {
        if(s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        recur(res, s, new ArrayList<>()) ;
        return res;
    }

    private void recur(List<String> res, String s, List<Integer> ip){//part will start 4 -> 0
        if(s.length() == 0 && ip.size() == 4) {
            res.add(cov(ip));
            return;
        }
        if(s.length() == 0 ||  ip.size() == 4) return;
        String one = s.substring(0,1);
        int vone = Integer.parseInt(one);
        ip.add(vone);
        recur(res, s.substring(1), ip);
        ip.remove(ip.size() - 1);

        if(s.length() >= 2){
            String two = s.substring(0,2);
            int vtwo = Integer.parseInt(two);
            if(vtwo >= 10) {
                ip.add(vtwo);
                recur(res, s.substring(2), ip);
                ip.remove(ip.size() - 1);
            }
        }

        if(s.length() >= 3){
            String three = s.substring(0,3);
            int vthree = Integer.parseInt(three);
            if(vthree >= 100 && vthree <= 255) {
                ip.add(vthree);
                recur(res, s.substring(3), ip);
                ip.remove(ip.size() - 1);
            }
        }


    }

    private String cov(List<Integer> ip){
        return ip.get(0) + "." +
                ip.get(1) + "." +
                ip.get(2) + "." +
                ip.get(3);
    }




    //q4
    //time o((m*n)^2) space o(n)
    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        if(words == null || words.length == 0 || board.length == 0) {
            return new ArrayList<>();
        }
        root = new TrieNode();
        boolean[][] visited = new boolean[board.length][board[0].length];
        buildTrie(words);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                TrieNode cur = root;
                dfs(board, cur, i, j, new StringBuilder(), res, visited);
            }
        }
        return new ArrayList<>(res);
    }

    private void buildTrie(String[] words) {
        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;

            char[] word = words[i].toCharArray();
            for (char ch : word) {
                // TrieNode next = cur.children[ch - 'a'];
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode();
                }
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }
    }

    private void dfs(char[][] board, TrieNode root, int i, int j, StringBuilder sb, Set<String> res, boolean[][] visited) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || root.children[board[i][j] - 'a'] == null) {
            return;
        }
        visited[i][j] = true;
        root = root.children[board[i][j] - 'a'];
        int len = sb.length();
        sb.append(board[i][j]);
        if(root.isWord) {
            res.add(sb.toString());
        }
        for(int[] dir : dirs) {
            dfs(board, root, i + dir[0], j + dir[1], sb, res, visited);
        }
        sb.setLength(len);
        visited[i][j] = false;
    }




    //q5 time o( n! )  space o(n)
    public int maxLength(List<String> arr) {
        if(arr == null || arr.size() == 0){
            return 0;
        }
        recur(arr, new StringBuilder(), 0);
        return max;
    }

    private int max = 0;

    private void recur(List<String> arr, StringBuilder path, int idx){
        if(isu(path)){
            max = Math.max(max, path.length());
        }
        if(idx == arr.size()){
            return;
        }
        int len = path.length();
        for(int i = idx ; i < arr.size(); i++){
            path.append(arr.get(i));
            recur(arr, path, i + 1);
            path.setLength(len);
        }
    }

    private boolean isu(StringBuilder str){
        int[] map = new int[26];
        for(int i = 0; i < str.length(); i++){
            int idx = str.charAt(i) - 'a';
            if(map[idx] > 0){
                return false;
            }
            map[idx]++;
        }
        return true;
    }


}
