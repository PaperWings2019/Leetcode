package LINKED_NODES.two_reverse;

class Solution {

    /* 
    * TASK:   swap pairs of node, for example 1->2->3->4 => 2->1->4->3
    * 
    * METHOD: see code
    * 
    * NOTE:   None
    * 
    * TIME/
    * SPACE:  O(N) / O(1) for iterative method | O(N) / O(N) for recursive method
    */

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode pos = new ListNode(-1, head);
        ListNode dummy = pos;
        while (pos != null) {
            if (pos.next != null && pos.next.next != null) {
                ListNode tmp = pos.next; // save as tmp
                pos.next = pos.next.next;
                tmp.next = pos.next.next;
                pos.next.next = tmp;
                pos = pos.next;
            }
            pos = pos.next;
        }
        return dummy.next;
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
        obj.printListNodes(obj.swapPairs(test));
    }
}
