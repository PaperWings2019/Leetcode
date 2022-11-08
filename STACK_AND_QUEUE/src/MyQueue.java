import java.util.Stack;

public class MyQueue {
    /*
     * TASK:   use two stacks to implement a queue (stack IN and stack OUT)
     *
     * METHOD: queue.push: push value to stack IN.
     *         queue.pop: if stack OUT is empty, pop ALL the elements in stack IN and push to stack OUT in order
     *         queue.peek: reuse queue.pop() but remember to push back the value
     *         queue.empty: only if two queues are both empty
     *
     * NOTE:   None
     *
     * TIME/
     * SPACE:  O(1) (average) / O(1)
     */
    Stack<Integer> stk1;
    Stack<Integer> stk2;

    public MyQueue() {
        stk1 = new Stack<>();
        stk2 = new Stack<>();
    }

    public void push(int x) {
        stk1.push(x);
    }

    public int pop() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.pop();
    }

    public int peek() {
        int result = this.pop();
        stk2.push(result);
        return result;
    }

    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }
}
