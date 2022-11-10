import java.util.Stack;

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
         * SPACE:  O(N) / O(1)
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
}
