import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class hashmaps {
    public boolean isAnagram(String s, String t) {
        /*
         * TASK:   judge if two strings are anagram. for example 'anagram' and 'naaagrm' are anagrams
         *
         * METHOD: use an array (simple hashmap) to store the frequency of occurrence of each letter,
         *         and during two traverses increment and reduce the frequency accordingly. if the array
         *         has negative frequency, return false, otherwise return true
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i ++) {
            freq[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i ++) {
            freq[t.charAt(i) - 'a'] --;
            // since length of two strings are equal
            // there must be some letter frequency less zero if false
            if (freq[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        /*
         * TASK:   find the intersection set of elements in two arrays
         *
         * METHOD: build an unordered set using nums1, compare the set with nums and return the result
         *
         * NOTE:   why not use array? because the elements may jump in a large range, using array cause
         *         waste of space
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> resSet = new HashSet<Integer>();
        for (int i: nums1) {
            set1.add(i);
        }
        for (int i: nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }
        return resSet.stream().mapToInt(x -> x).toArray();
    }

    public boolean isHappy(int n) {
        /*
         * TASK:   find if a number is happy number
         *         input：n = 19
         *         output：true
         *         explanation：
         *         12 + 92 = 82
         *         82 + 22 = 68
         *         62 + 82 = 100
         *         12 + 02 + 02 = 1
         *
         * METHOD: using a set to record the possible result number, if there is a repeated number,
         *         then return false. if conditions satisfied, return true
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(X) / O(N)
         */
        if (n == 1) {
            return true;
        }
        Set<Integer> set = new HashSet<Integer>();
        int sum = 0;
        int tmp = n;
        int rem = 0;
        while (tmp != 0) {
            rem = tmp % 10;
            tmp /= 10;
            sum += rem * rem;
        }
        while (!set.contains(sum)) {
            if (sum == 1) {
                return true;
            }
            set.add(sum);
            tmp = sum;
            sum = 0;
            while (tmp != 0) {
                rem = tmp % 10;
                tmp /= 10;
                sum += rem * rem;
            }
        }
        return false;
    }

    public int[] twoSum(int[] nums, int target) {
        /*
         * TASK:   find two numbers whose sum is target in an array
         *
         * METHOD: build a map with <nums[i], i> bindings. when traversing the array,
         *         if there is a matched number update the result
         *
         * NOTE:   first judge if the requirements are satisfied, then add new binding
         *         otherwise may cause error
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        int[] res = new int[2];
        int flag = 1;
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i ++) {
            if (map1.containsKey(target - nums[i])) { // first step
                res[0] = map1.get(target - nums[i]);
                res[1] = i;

            }
            map1.put(nums[i], i); // second step
        }
        return res;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        /*
         * TASK:   find how many groups of four numbers in each independent array, whose sums would be zero
         *
         * METHOD: build a hashmap, first double for loop in nums1 and nums2, store the bindings of
         *         nums[i] + nums[j] and times of occurrences, then double for loop in nums3 and nums4,
         *         find if there exists a key (0 - nums3[i] - nums4[j]), if so, increment the count by
         *         corresponding value found in the hashmap
         *
         * NOTE:   Another more difficult question will be the Leetcode No.18
         *
         * TIME/
         * SPACE:  O(N ^ 2) / O(N)
         */
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        int count = 0;
        for (int i = 0; i < nums1.length; i ++) {
            for (int j = 0; j < nums2.length; j ++) {
                map1.put(nums2[i] + nums1[j], map1.getOrDefault(nums2[i] + nums1[j], 0) + 1);
            }
        }
        for (int i = 0; i < nums1.length; i ++) {
            for (int j = 0; j < nums2.length; j ++) {
                int temp = 0 - (nums3[i] + nums4[j]);
                if (map1.containsKey(temp)) {
                    count += map1.get(temp);
                }
            }
        }
        return count;
    }
}
