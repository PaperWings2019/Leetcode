package square_array;

import java.util.Arrays;

class Solution {
    /* 
     * TASK:   math.square all elements in an ascending array,
     *         and make sure that the result array is in ascending order
     * 
     * METHOD: create a new array in same length, with an initial pointer at the final index,
     *         since the squared maximum value in original array is either at the very left or very right ends,
     *         just compare the two ends and save the larger one in new array
     * 
     * NOTE:   None
     * 
     * TIME/
     * SPACE:  O(N) / O(N)
     */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int k = nums.length - 1;
        int i = 0; int j = nums.length - 1;
        while (i <= j) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];
            if (a <= b) {
                res[k--] = b;
                j--;
            } else {
                res[k --] = a;
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
        Solution obj = new Solution();
        int[] newArray = obj.sortedSquares(nums);
        System.out.println(Arrays.toString(newArray));
    }
}