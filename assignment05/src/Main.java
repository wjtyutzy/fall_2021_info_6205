import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {


    }

    //Assignment 5
    //Question 1: String to Integer (atoi)
    //time o(n) space o(1)
    public int myAtoi(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }

        str = str.trim();
        if(str.length() == 0){
            return 0;
        }

        int pointer = 0;
        long res = 0;
        long sign = 1;
        if(str.charAt(0) == '-'){
            sign = -1;
            pointer++;
        } else if(str.charAt(0) == '+'){
            sign = 1;
            pointer++;
        }
        //if start is a number then no need to add.
        //start to traverse the string
        for(int i = pointer; i < str.length(); i++){
            if(isNum(str.charAt(i))) {
                res  = res * 10 + (str.charAt(i) - '0');
            } else {//not number
                break;
            }

            if(res > Integer.MAX_VALUE){
                break;
            }
        }

        if(sign == -1 && res > Integer.MAX_VALUE){
            return Integer.MIN_VALUE;
        } else if(res > Integer.MAX_VALUE ){
            return Integer.MAX_VALUE;
        }

        return (int)(res * sign);
    }

    private boolean isNum(char c){
        return c >= '0' && c <= '9';
    }







    //Question 2: Reformat Date

    //time o(n) space o(1)
    public String reformatDate(String date) {
        //String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        String[] input = date.split(" ");

        //get day
        int day = 0;
        for(int i = 0; i < input[0].length(); i++) {
            char c = input[0].charAt(i);
            if( c >= '0' && c <= '9') {
                day = day * 10 + (c - '0');
            }
        }
        String d1 = day >= 10 ? day + "" : "0" + day;
        //get month
        int m = parseMo(input[1]);
        String m1 = m < 10 ? "0" + m : m + "";

        //get year
        return input[2] + "-" + m1 + "-" + d1;
    }
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private int parseMo(String month){
        for(int i = 0; i < months.length; i++) {
            if(months[i].equals(month)) {
                return  i+1;
            }
        }
        return -1;
    }







    //Question 3: Valid Paranthese

    //time o(n) space o(n)
    public boolean validParenthese(String str){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                } else if(c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                } else {
                    // case }
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }








    //Question 4: Count and Say

    // time o(n) space o(n)
    public String countAndSay(int n) {
        if(n == 1) {
            return 1 + "";
        }

        String cur = oneStep("1");
        for(int i = 3; i <= n; i++){
            cur = oneStep(cur);
        }
        return cur;
    }

    private String oneStep(String cur){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < cur.length()) {
            char baceChar = cur.charAt(i);
            int base = baceChar - '0';
            int num = 1;
            int j = i + 1;
            while(j < cur.length() && baceChar == cur.charAt(j)) {
                j++;
                num++;
            }
            sb.append(num);
            sb.append(base);
            i = j;
        }
        return sb.toString();
    }






    //Question 5: Best Time to Buy and Sell Stock

    // time o(n) space o(n)
    public int buyAndSell(int[] nums){
        if(nums.length == 0) {
            return 0;
        }
        int min = nums[0];
        int[] dp = new int[nums.length];

        for(int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            dp[i] = Math.max(dp[i - 1], nums[i] - min);
        }

        return dp[nums.length - 1];
    }



    //Question 6: Find Pivot Index

    //time o(n) space o(1)
    public int getPivot(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int tmp = 0;
        for(int i  = 0; i < nums.length; i++) {
            if(tmp * 2 == sum - nums[i] ) {
                return i;
            }
            tmp += nums[i];
        }

        return -1;
    }







    //Question 7: High Five

    //time n(nlogn) space o(n)
    public int[][] highFive(int[][] grid) {
        Arrays.sort(grid, (a, b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        int curId = grid[0][0];

        for(int[] in : grid){
            if(count < 5 && in[0] == curId) {
                sum += in[1];
                count++;
            }
            if (in[0] != curId){
                map.put(curId, sum / 5);
                curId = in[0];
                sum = in[1];
                count = 1;
            }
        }
        map.put(curId, sum / 5);//bug here!
        int[][] res = new int[map.size()][2];
        int i = 0;
        System.out.println(map.size());
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res[i][0] = entry.getKey();
            res[i][1] = entry.getValue();
            i++;
        }
        Arrays.sort(res, (a,b) -> a[0] - b[0]);
        return res;
    }






    //Question 8: Search in Rotated Sorted Array

    //time o(logn) space o(1)
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > nums[right]) {
                if(nums[left] <= target && nums[mid] > target ) {
                    //at left to mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[right] >= target && nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


}
