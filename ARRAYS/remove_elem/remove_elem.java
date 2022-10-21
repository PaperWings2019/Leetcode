package remove_elem;
import java.util.Arrays;

class Solution {
    /* 
     * TASK:   remove all occurrences of a specific value in an array, with space complexity O(1)
     * 
     * METHOD: use two pointers, one pointing to result array index (slow),
     *         the other pointing to the original one (fast)
     *         move the fast pointer until the pointers points to a value not equal to 'val'
     *         and assign corresponding value to the slow one
     * 
     * NOTE:   all operations are implemented on the original array
     * 
     * TIME/
     * SPACE:  O(N) / O(1)
     */
    public int removeElement(int[] nums, int val) {
        int left = 0; int right = 0; int n = nums.length;
        while (right < n) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left += 1;
                right += 1;
            } else {
                right += 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        Solution obj = new Solution();
        int newLength = obj.removeElement(nums, val);
        int[] newArray = Arrays.copyOfRange(nums, 0, newLength);
        System.out.println(Arrays.toString(newArray));
    }
}