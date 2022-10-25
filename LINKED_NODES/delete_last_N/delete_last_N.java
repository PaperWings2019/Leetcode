package LINKED_NODES.delete_last_N;

class Solution {

    /* 
    * TASK:   delete the last nth node from a linked-node
    * 
    * METHOD: create two pointers, the fast one should have n-1 nodes away from the slow one,
    *         move two together, when the fast one moved to the end, the next node of slow one is 
    *         the one we want to delete
    * 
    * NOTE:   create a dummy node before head
    * 
    * TIME/
    * SPACE:  O(N) / O(1) 
    */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(-1, head); // dummy
        ListNode fast = dummy;
        ListNode slow = dummy;
        int i = 0;
        while (i ++ < n) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

    public void printListNodes(ListNode head) {
        ListNode pos = head;
        if (pos == null) {
            System.out.println("the linknode is empty");
        }
        while (pos != null) {
            System.out.println(pos.val);
            pos = pos.next;
        }
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        int[] t = {1, 2, 3, 4, 5};
        ListNode test = obj.createLNfromarray(t);
        obj.printListNodes(obj.removeNthFromEnd(test, 2));
    }
}