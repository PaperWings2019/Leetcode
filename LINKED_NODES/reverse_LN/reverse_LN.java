package LINKED_NODES.reverse_LN;

class Solution {

    /* 
    * TASK:   reverse the linked nodes
    * 
    * METHOD: Interative method and recursive method, see code
    * 
    * NOTE:   None
    * 
    * TIME/
    * SPACE:  O(N) / O(1) for iterative method | O(N) / O(N) for recursive method
    */

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // OR IN RECURSIVE METHOD

    // ListNode t = new ListNode(-1, null); // dummy
    // public ListNode reverseList(ListNode head) {
    //     if (head == null) {
    //         return head;
    //     }
    //     ListNode p = new ListNode(-1, head);
    //     ListNode tmp = t;
    //     helper(p);
        
    //     p.next.next = null;
        
    //     return tmp.next;
    // }

    // public void helper(ListNode pos) {
    //     if (pos != null && pos.next != null) {
    //         helper(pos.next);
    //         t = t.next;
    //         t.next = pos;
    //         return;
    //     } else {
    //         t.next = pos;
    //         return;
    //     }
    // }

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
        obj.printListNodes(obj.reverseList(test));
    }
}

