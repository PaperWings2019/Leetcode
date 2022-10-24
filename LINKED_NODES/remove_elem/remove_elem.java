package LINKED_NODES.remove_elem;

class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeElements(ListNode head, int val) {
        /* 
        * TASK:   remove all nodes that have value 'val'
        * 
        * METHOD: find the preceding node, whose 'next' should be next node whose value is not 'val'
        * 
        * NOTE:   None
        * 
        * TIME/
        * SPACE:  O(N) / O(1) where C is the length of the character set
        */
        ListNode dummy = new ListNode(-1, head);
        ListNode pos = dummy;
        while (pos != null) {
            while (pos.next != null && pos.next.val == val) {
                pos.next = pos.next.next;
            }
            pos = pos.next;
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
        System.out.println(obj);
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = obj.createLNfromarray(arr);
        obj.printListNodes(obj.removeElements(head, 6));
    }
}
