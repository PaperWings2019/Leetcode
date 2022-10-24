package sliding_window3;

import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

class Solution {

    HashMap<Character, Integer> countT = new HashMap<Character, Integer>();
    HashMap<Character, Integer> countS = new HashMap<Character, Integer>();

    public String minWindow(String s, String t) {

        /* 
        * TASK:   find the only substring in 's' that contains all occurrences in 't'
        * 
        * METHOD: use double pointers (sliding window). right pointer add more characters by moving right,
        *         until the closed subarray contains all occurrences of char in 't', then moving left pointer,
        *         keep updating the position of ansL and ansR, until requirement is not satisfied.
        * 
        * NOTE:   changes in two hashtable are important
        * 
        * TIME/
        * SPACE:  O(C * |t| + |s|) / O(C) where C is the length of the character set
        */
        int right = -1; int left = 0;
        int ansL = -1; int ansR = -1;
        int len = Integer.MAX_VALUE;

        // initialize map for string t
        for (int i = 0; i < t.length(); i ++) {
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (right ++ < s.length()) {
            if (right < s.length() && countT.containsKey(s.charAt(right))){
                countS.put(s.charAt(right), countS.getOrDefault(s.charAt(right), 0) + 1);
            }
            while (check() && left <= right) {
                if (right - left + 1 < len) {
                    len = right - left + 1;
                    ansL = left;
                    ansR = right;
                }
                if (countT.containsKey(s.charAt(left))) {
                    countS.put(s.charAt(left), countS.getOrDefault(s.charAt(left), 0) - 1);
                }
                left ++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR + 1);
    }

    public boolean check() {
        Iterator iter = countT.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (countS.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

}