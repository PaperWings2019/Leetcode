package sliding_window;

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
    /* 
     * TASK:   find a *sub-array* whose sum is not less than the target value, and return its length
     *         if not found return 0
     * 
     * METHOD: use a sliding window (or double pointers), moving the right pointer one by one,
     *         during the process check if the closed sub-array statisfy the requirement,
     *         if so, keep updating the result and moving the left pointer to the next right index, until
     *         the requirement is no longer satisfied
     * 
     * NOTE:   the 'j' in the for loop refers to the right pointer
     * 
     * TIME/
     * SPACE:  O(N) / O(1)
     */
        int n = nums.length;
        int i = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j ++) {
            // sum is gradually accumulated
            sum += nums[j];
            while (sum >= target) {
                // note that the length should be added with an extra one
                result = Math.min(result, j - i + 1);
                // reduce the sum and move the left pointer
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        Solution obj = new Solution();
        System.out.println(obj.minSubArrayLen(7, nums));
    }
}
