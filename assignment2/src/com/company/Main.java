package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

    }
    //1.Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target
    //You may assume that each input would have exactly once solution,and you may not use the same element twice.
    //You can return the answer in any order.Only one valid answer exists.

    //time:o(n)  space:o(n)
    public int[] twoSumIndice(int[] nums,int target){
        int[] res={-1,-1};
        //num and index
        //t=s1+s2->s1==t-s2
        Map<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                res[0]=map.get(nums[i]);
                res[1]=i;
                break;
            }
            int s = target-nums[i];
            map.put(s,i);
        }
       return res;
    }


    //2. Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the  minimum number of conference rooms required.
//Constraints:
//• 1 <= intervals.length <= 104
//• 0 <= starti < endi <= 106

    //time o(nlogn)  space o(n)
    public int minMeetingRoom(int[][] intervals) {
        int[] st = new int[intervals.length];
        int[] ed = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            st[i] = intervals[i][0];
            ed[i] = intervals[i][1];
        }
        Arrays.sort(st);
        Arrays.sort(ed);

        int res = 0;
        int j = 0;
        for (int i = 0; i < st.length; i++) {
            if (st[i] < ed[j]) {
                res++;
            } else {
                j++;
            }
        }
        return res;
    }


//3. Given two integer arrays nums1 and nums2,
// return an array of their intersection.
// Each element in the result must be unique and you may return the result in any order.

//Constraints:
//• 1 <= nums1.length, nums2.length <= 1000
//• 0 <= nums1[i], nums2[i] <= 1000


    //time o(nlogn) space o(n)
    public List<Integer> getIntersection(int[] nums1, int[] nums2) {
        Set<Integer> res = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        /*
         * 1 3 5 8
         * 3 8 9 9
         * */
        int i = 0, j = 0;
        while (i < nums1.length) {
            if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return new ArrayList<>(res);
    }


//4. Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
//• 1 <= nums.length <= 5 * 104
//• -109 <= nums[i] <= 109

    //time o(nlogn) space o(1)
    public List<Integer> getAllMoreThanN3(int[] nums) {
        /*
         * 0 1 2 3 4 5
         * 1 1 2 2 3 3
         * */
        Arrays.sort(nums);
        int lenN3 = nums.length / 3 + 1;
        int i = 0;
        List<Integer> res = new ArrayList<>();
        while (i < nums.length) {
            int targIndex = i + lenN3 - 1;
            if (targIndex < nums.length && nums[targIndex] == nums[i]) {
                res.add(nums[i]);
                i = targIndex + 1;
            } else {
                i++;
            }
        }
        return res;
    }


//5. Given an array of integers nums sorted in ascending order,
// find the starting and ending position of a given target value.
// If target is not found in the array, return [-1, -1].
//Constraints:
//• 0 <= nums.length <= 105
//• -109 <= nums[i] <= 109
//• nums is a non-decreasing array.
//• -109 <= target <= 109

    //time o(logn)   space o(1)
    public int[] findStEd(int[] nums, int target) {
        int st = -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                st = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (st == -1) {
            return new int[]{-1, -1};
        }

        int ed = -1;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ed = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return new int[]{st, ed};
    }


//6. Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column wise,
// return the number of negative numbers in grid.
//Constraints:
//• m == grid.length
//• n == grid[i].length
//• 1 <= m, n <= 100
//• -100 <= grid[i][j] <= 100

    // time o(m + n)  (m is col and n is row);  space o(1)
    public int countNegNum(int[][] grid) {
        int n = grid[0].length;
        int col = n - 1;
        int row = 0;

        int res = 0;
        while (row < grid.length) {

            while (col >= 0 && grid[row][col] < 0) {
                col--;
            }
            res += n - col - 1;
            row++;
        }
        return res;
    }


//7. A peak element is an element that is strictly greater than its neighbors.
// Given an integer array nums,  find a peak element,
// and return its index.
// If the array contains multiple peaks, return the index to  any of the peaks.
//You may imagine that nums[-1] = nums[n] = -infinity
//Example 1:
//Input: nums = [1,2,3,1]
//Output: 2
//Explanation: 3 is a peak element and your function should return the index number 2.
//Example 2:
//Input: nums = [1,2,1,3,5,6,4]
//Output: 5
//Explanation: Your function can return either index number 1 where the peak element is 2, or index  number 5 where the peak element is 6.
//Constraints:
//• 1 <= nums.length <= 1000
//• -231 <= nums[i] <= 231 - 1
//• nums[i] != nums[i + 1] for all valid i.

    //time o(logn) space o(1)
    public int findPeak(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPeak(nums, mid)) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }

    private boolean isPeak(int[] nums, int i) {
        if ((i == 0 && nums[i + 1] < nums[i])
                || (i == nums.length - 1 && nums[i - 1] < nums[i])
                || (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])) {
            return true;
        }
        return false;
    }


//8. Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
// inclusive. There is only one repeated number in nums,
// return this repeated number.
//Constraints:
//• 1 <= n <= 105
//• nums.length == n + 1
//• 1 <= nums[i] <= n
//• All the integers in nums appear only once except for precisely one integer which appears  two or more times.

    // time o(nlogn) space o(n)
    public int findDupNumber(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //1 2 2 3
            if (isDup(nums, mid)) {
                return nums[mid];
            } else if (nums[mid] < mid + 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private boolean isDup(int[] nums, int i) {
        if ((i > 0 && nums[i - 1] == nums[i])
                || (i < nums.length && nums[i + 1] == nums[i])) {
            return true;
        }
        return false;
    }


//9. Given an array arr of positive integers sorted in a strictly increasing order,
// and an integer k. Find the kth positive integer that is missing from this array.
//Example 1:
//Input: arr = [2,3,4,7,11], k = 5
//           1        5 6  8 9 10 11  at 7 missing 3 nums at 11 missing 6nums
//Output: 9
//Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive  integer is 9.
//Example 2:
//Input: arr = [1,2,3,4], k = 2
//Output: 6
//Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
//Constraints:
//• 1 <= arr.length <= 1000
//• 1 <= arr[i] <= 1000
//• 1 <= k <= 1000
//• arr[i] < arr[j] for 1 <= i < j <= arr.length

    //time(o(logn)) space o(1)
    public int findMissingNum(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        if (countMissed(nums, nums.length - 1) < k) {
            return nums[nums.length - 1] + (k - countMissed(nums, nums.length - 1));
        }
        int p = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (countMissed(nums, mid) > k) {
                p = mid;
                right = mid - 1;
            } else if (countMissed(nums, mid) < k) {
                left = mid + 1;
            } else {
                return nums[mid] - 1;
            }
        }
        if (p == 0) {
            return k;
        }

        //return nums[p - 1] + (k - (nums[p-1] - (p-1) -1)); -> p + k ?
        return p + k;

    }

    private int countMissed(int[] nums, int idx) {
        return nums[idx] - idx - 1;
    }

}
