import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class double_pointers {
    public List<List<Integer>> threeSum(int[] nums) {
        /*
         * TASK:   find three different indexes that the sum of their corresponding integers equals to zero
         *
         * METHOD: Use double-pointer method.
         *
         * NOTE:   Note that some branches can be cut off
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // cut branch
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (nums[i] + nums[n - 1] + nums[n - 2] < 0) {
                continue;
            }

            for (int j = i + 1; j < n - 1; j ++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int right = n - 1;
                while (right > j && nums[i] + nums[j] + nums[right] > 0) {
                    right --;
                }
                if (right == j) {
                    break;
                }
                if (nums[i] + nums[j] + nums[right] == 0) {
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[right]);
                    res.add(l);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        /*
         * TASK:   find four different indexes that the sum of their corresponding integers equals to target
         *
         * METHOD: Use double-pointer method.
         *
         * NOTE:   Note that some branches can be cut off.
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < n - 3; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // cut branch
            // also note that there might be overflow in the addition
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j ++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // cut branch
                // also note that there might be overflow in the addition
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[j] < target) {
                    continue;
                }
                for (int y = j + 1; y < n - 1; y ++) {
                    if (y > j + 1 && nums[y] == nums[y - 1]) {
                        continue;
                    }
                    int right = n - 1;
                    while (right > y && nums[i] + nums[j] + nums[y] + nums[right] > target){
                        right --;
                    }
                    if (right == y) {
                        break;
                    }
                    if (nums[i] + nums[j] + nums[y] + nums[right] == target) {
                        List<Integer> l = new ArrayList<Integer>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[y]);
                        l.add(nums[right]);
                        res.add(l);
                    }
                }
            }
        }
        return res;
    }
}
