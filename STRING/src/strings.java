public class strings {
    public void reverseString(char[] s) {
        /*
         * TASK:   reverse a string which is stored as char array
         *
         * METHOD: use double pointers with one at start and one at end
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        int left = 0;
        int right = s.length - 1;
        Character temp;
        while (left <= right) {
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left ++;
            right --;
        }
    }

    public String reverseStr(String s, int k) {
        /*
         * TASK:   reverse a substring with length k every other k places
         *
         * METHOD: see code.
         *
         * NOTE:   If we are dealing with something happens regularly, consider using for loops
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        char[] ch = s.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i += 2 * k) {
            int start = i;
            int end = Math.min(start + k - 1, len - 1); // decide the end position
            while (start < end) {
                // reversing the substring ...
                // but what the f ?
                ch[start] ^= ch[end];
                ch[end] ^= ch[start];
                ch[start] ^= ch[end];
                start ++;
                end --;
            }
        }
        return new String(ch);
    }

    public String replaceSpace(String s) {
        /*
         * TASK:   replace all spaces in a string with '%20'
         *
         * METHOD: Using double pointers, first decide how many spaces are needed, i.e. one space = two
         *         more spaces, to extend the original array. Move the pointers, when the left one encounters
         *         space, the right one fills with '%20', otherwise the right one just copy the left one. See
         *         code and attached image for detail.
         *
         * NOTE:   When it comes to filling the array, we can always use the same method. why not traverse the
         *         array from start to end (but in reverse order) ? because in that way every time we need to
         *         insert an element, all the elements behind need to shift, which causes a O(N ^ 2) time
         *         complexity
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder str = new StringBuilder();
        // calculating how many spaces there are
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        if (str.length() == 0) {
            return s;
        }
        int left = s.length() - 1; // set left pointer to be the end of original string
        s += str.toString();
        int right = s.length() - 1; //  set left pointer to be the end of extended string
        char[] ch = s.toCharArray();
        while (left >= 0) {
            if (ch[left] == ' ') {
                ch[right --] = '0';
                ch[right --] = '2';
                ch[right] = '%';
            } else {
                ch[right] = ch[left];
            }
            left --;
            right --;
        }
        return new String(ch);
    }

    public String reverseWords(String s) {
        /*
         * TASK:   reverse each word in a string, each word is separated by a space, there should be no extra
         *         spaces before and behind the string, and also between words
         *
         * METHOD: First reverse all the String. Them, setting double pointers, one set at the beginning of the string,
         *         one set at the first non-space char, and copy chars that are not spaces, adding single space
         *         when copied one word. Finally, reverse each word accordingly.
         *
         *
         * NOTE:   The method should have been O(1) space, but java does not allow to modify the string
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        char[] ch = s.toCharArray();
        int k = 0;
        int pos = 0;
        reverse(ch, 0, s.length() - 1);
        while (pos < s.length() && ch[pos] == ' ') { // move the pos until it is not space
            pos ++;
        }
        if (pos == s.length()) { // all the chars are spaces
            return "";
        } else {
            while (pos < s.length()) {
                if (ch[pos] != ' ') { // copy chars that are not spaces
                    ch[k] = ch[pos];
                    pos ++;
                    k ++;
                } else {
                    while (pos < s.length() && ch[pos] == ' ' ) {pos ++;} // move pos until it is not space
                    if (pos < s.length() && ch[pos] != ' ') { // append space between words
                        ch[k ++] = ' ';
                    }
                }
            }
            pos = 0;
            while (pos < k) { // reversing each word
                int end = pos;
                while (end < k && ch[end] != ' ') {end ++;}
                reverse(ch, pos, end - 1);
                pos = end + 1;
            }
        }
        return new String(ch, 0, k);
    }

    public void reverse(char[] ch, int low, int high) {
        while (low < high) {
            ch[low] ^= ch[high];
            ch[high] ^= ch[low];
            ch[low] ^= ch[high];
            low ++;
            high --;
        }
    }

    public String reverseLeftWords(String s, int n) {
        /*
         * TASK:   move the first n chars to the end of the string
         *
         * METHOD: Three reverse operation: First reverse the whole string, then reverse the last n
         *         chars and s.length() - n chars
         *
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(N) / O(1)
         */
        char[] ch = s.toCharArray();
        reverse(ch, 0, s.length() - 1);
        reverse(ch, 0, s.length() - 1 - n);
        reverse(ch, s.length() - n, s.length() - 1);
        return new String(ch);
    }

    public int strStr(String haystack, String needle) {
        /*
         * TASK:   find the first occurrence of needle in haystack
         *
         * METHOD: See 'KMP algorithm' on Internet: https://www.zhihu.com/question/21923021/answer/281346746
         *
         * NOTE:   In build next method, j points to the prefix and i points to the suffix
         *
         * TIME/
         * SPACE:  O(N + M) / O(M) where N is the size of haystack, M is the size of needle
         */
        int i = 0; int j = 0;
        int[] next = build_next(needle.toCharArray());
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i ++;
                j ++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public int[] build_next(char[] needle) {
        // j points to the prefix
        // i points to the suffix
        int[] next = new int[needle.length];
        next[0] = -1;
        int i = 0; int j = -1;
        while (i < needle.length - 1) {
            if (j == -1 || needle[i] == needle[j]) {
                i ++;
                j ++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public boolean repeatedSubstringPattern(String s) {
        /*
         * TASK:   find out if a string is repeated version of a substring
         *
         * METHOD: concatenate two 's', namely 's + s', there should be a 's' in the middle of
         *         that string, whose beginning index is not 0 or length of s.
         *
         * NOTE:   alternatively, KMP is used since it is to find a substring in a main string
         *
         * TIME/
         * SPACE:  O(N) / O(1) where N is the size of haystack, M is the size of needle
         */
        return (s + s).indexOf(s, 1) != s.length();
    }
}
