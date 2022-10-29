package Others;

public class find_duplicate_num {
    class Solution {

    /*
    * TASK:   there are totally (n + 1) numbers in an array, and all of them are in [1, n], find the 
    *         only one duplicate number in the array with time O(N) and space O(1), do not modify the
    *         array
    * 
    * METHOD: build a graph using Floyd's method, that is, node i -> node nums[i]. Since all of them are
    *         in [1, n], there will be no any index out of the array range. After building the graph, it 
    *         can be predicted that there will be more than one arc pointing node nums[x], where x is the 
    *         duplicate number. Then it forms a loop in the graph, we want to find the first node in the loop.
    * 
    * NOTE:   Essentially, the problem is the same as the one requires us to detect the loop in linked node
    * 
    * TIME/
    * SPACE:  O(N) / O(1) 
    */

        public int findDuplicate(int[] nums) {
            int slow = 0;
            int fast = 0;
            do {
                slow = nums[slow];
                fast = nums[fast];
                fast = nums[fast];
            } while (fast != slow);
            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
    }
}