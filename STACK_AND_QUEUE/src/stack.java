import java.util.*;

public class stack {
    public boolean isValid(String s) {
        /*
         * TASK:   judge is a string valid sequence of parenthesis
         *
         * METHOD: use stack and traverse the string s. each time concatenate the s.peek() and s.charAt(i)
         *         if equals to "()", "[]" or "{}", pop the stack, instead push the new value. check if the
         *         stack is empty finally.
         *
         * NOTE:   Strings can not use "==" to check equality
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        Stack<Character> stk = new Stack<>();
        int i = 0;
        char top;
        while (i < s.length()) {
            if (!stk.isEmpty()) {
                top = stk.peek();
            } else {
                top = 'd'; //DUMMY
            }
            char l = s.charAt(i++);
            String t = Character.toString(top) + Character.toString(l);
            System.out.println(i + t);
            if (t == "()" || t == "[]" || t == "{}") {
                System.out.println(1);
                stk.pop();
            } else {
                stk.push(l);
            }
        }
        return stk.isEmpty();
    }

    public String removeDuplicates(String s) {
        /*
         * TASK:   return the string where removes every two same adjacent characters and keeps the process
         *         until not applicable
         *
         * METHOD: use stack to save chars. if stk.peek() == s.charAt(i), stk.pop(), else stk.push()
         *
         * NOTE:   Strings can not use "==" to check equality
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        Stack<Character> stk = new Stack<>();
        int pos = 0;
        while (pos != s.length()) {
            char t = s.charAt(pos++);
            if (!stk.isEmpty()) {
                char a = stk.peek();
                if (a == t) {
                    stk.pop();
                } else{
                    stk.push(t);
                }
            } else {
                stk.push(t);
            }
        }
        StringBuffer res = new StringBuffer();
        while (!stk.isEmpty()) {
            res.append(stk.pop());
        }
        return new String(res.reverse());
    }

    public int evalRPN(String[] tokens) {
        /*
         * TASK:   See link: https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
         *
         * METHOD: use stack to perform the operations. if operation. get the first two numbers to get the
         *         result and push the result back, otherwise just push
         *
         * NOTE:   Strings can not use "==" to check equality
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        int pos = 0;
        Stack<String> stack = new Stack<>();
        while (pos < tokens.length) {
            String t = tokens[pos++];
            if (t.equals("+")) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                int res = op1 + op2;
                stack.push(Integer.toString(res));
            } else if (t.equals("-")) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                int res = op2 - op1;
                stack.push(Integer.toString(res));
            } else if (t.equals("*")) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                int res = op2 * op1;
                stack.push(Integer.toString(res));
            } else if (t.equals("/")) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                int res = op2 / op1;
                stack.push(Integer.toString(res));
            } else {
                stack.push(t);
            }
        }
        return Integer.parseInt(stack.peek());
    }


    class myQueue {
        Deque<Integer> myq = new LinkedList<>();
        void push(int val) {
            while (!myq.isEmpty() && myq.getLast() < val) {
                myq.removeLast();
            }
            myq.add(val);
        }
        void pop(int val) {
            if (!myq.isEmpty() && val == myq.peek()) {
                myq.poll();
            }
        }
        int peek() {
            return myq.peek();
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        /*
         * TASK:   find the maximum value in the sliding window with size k, and return an array recording
         *         each maximum value in the sliding window
         *
         * METHOD: use double-ended queue to implement a monotonic queue. redefine the behavior of push and pop:
         *         monotonic queue: a queue where the exit is the largest number and subsequent candidate numbers
         *              are in descending order
         *         pop: when the window moves, judge if the exit (the largest number) equals to the number about
         *              to be removed from the window, if so, pop the exit element.
         *         push: when the window moves, judge if the entrance (the smallest candidate number) is smaller
         *              than the number about to be included in the window, if so, remove the entrance until it
         *              is larger than the number or the queue is empty.
         *         peek: return the exit of the queue, namely the maximum number in the window
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(N) / O(k)
         */
        myQueue q = new myQueue();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int pos = 0;
        if (n == 0 || k == 0) {
            return res;
        }
        for (int i = 0; i < k; i ++) {
            q.push(nums[i]);
        }
        res[pos++] = q.peek();
        for (int i = k; i < n; i ++) {
            q.pop(nums[i - k]);
            q.push(nums[i]);
            res[pos++] = q.peek();
        }
        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        /*
         * TASK:   find the k elements with the highest frequency
         *
         * METHOD: Use hashmap to record the frequency, and use a big priority queue to sort the recorded
         *         pairs of number -> frequency. finally the first k elements are the anwser
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(N) / O(N)
         */
        Map<Integer,Integer> map = new HashMap<>();//key为数组元素值,val为对应出现次数
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair2[1]-pair1[1]);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){//大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] ans = new int[k];
        for(int i=0;i<k;i++){//依次从队头弹出k个,就是出现频率前k高的元素
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}
