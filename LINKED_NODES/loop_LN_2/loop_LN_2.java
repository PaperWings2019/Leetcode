package LINKED_NODES.loop_LN_2;

class Solution {

    /*
    * TASK:   find if there is a loop existing in the linked node, if so, find the first node in loop
    * 
    * METHOD: set the distance between head and first node in loop to be 'x', set the distance between
    *         first node and first collide node in the loop to be y, set the difference of the rest 
    *         loop to be z. Since the journey of fast pointer is twice as much as that of slow pointer,
    *         we have (x + y) * 2 = x + y + n * (z + y), or x + y = n * (z + y). we want the distance
    *         x, where x = (n - 1) * (z + y) + z, which means if we set two pointers, one at collide node
    *         and one at head, moving two pointers together, they will collide at the first node in the 
    *         loop. for details see the attached image.
    * 
    * NOTE:   create a dummy node before head
    * 
    * TIME/
    * SPACE:  O(N) / O(1) 
    */

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        int flag = 1;
        while ((slow != fast || flag == 1) && slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if (flag == 1) {
                flag = 0;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        
        slow = dummy;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode createLNfromarray(int[] arr) {
        int n = arr.length;
        ListNode dummy = new ListNode(-1, null);
        ListNode pos = dummy;
        int i = 0;
        while (i < n) {
            pos.next = new ListNode(arr[i], null);
            pos = pos.next;
            i ++;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = obj.createLNfromarray(arr);
        head.next.next.next.next.next = head.next.next;
        System.out.println(obj.detectCycle(head).val);
    }
}