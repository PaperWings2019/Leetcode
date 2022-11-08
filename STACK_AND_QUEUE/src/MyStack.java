import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    /*
     * TASK:   use a queue to implement a stack
     *
     * METHOD: maintain a variable 'size'
     *         queue.push: push value to queue, size += 1
     *         queue.pop: repeat (size - 1) times of pop and then push back, so that the last element comes first
     *                    now pop the element. size -= 1
     *         queue.peek: reuse stack.pop() but remember to push back the value using stack.push(x)
     *         queue.empty: if queue is empty
     *
     * NOTE:   None
     *
     * TIME/
     * SPACE:  O(1) (average) / O(1)
     */
    Queue<Integer> q;
    int size;
    public MyStack() {
        q = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        size += 1;
        q.offer(x);
    }

    public int pop() {
        int i = 0;
        while (i < size - 1) {
            int tmp = q.poll();
            q.offer(tmp);
            i ++;
        }
        size -= 1;
        return q.poll();
    }

    public int top() {
        int result = this.pop();
        this.push(result);
        return result;
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
