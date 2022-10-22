package sliding_window2;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int totalFruit(int[] fruits) {
    /* 
     * TASK:   find a *sub-array* that does not have three or more ( kinds<=2 ) kinds of elements
     *         return the maximum length of the subarray
     * 
     * METHOD: use a sliding window (or double pointers), moving the right pointer one by one,
     *         during the process check if the closed sub-array statisfy the requirement,
     *         if not, keep updating the hash table (value -> count) until the requirement is satisfied,
     *         also keep record of maximum length of subarray
     * 
     * NOTE:   if the corresponding count in the hash table is zero, the value-count binding relationship
     *         should be removed
     * 
     * TIME/
     * SPACE:  O(N) / O(N)
     */
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits = {3,3,3,1,2,1,1,2,3,3,4};
        Solution obj = new Solution();
        System.out.println(obj.totalFruit(fruits));
    }
}