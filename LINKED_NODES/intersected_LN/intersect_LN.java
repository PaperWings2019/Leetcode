package LINKED_NODES.intersected_LN;



class Solution {

    /*
    * TASK:   judge if two linked nodes are intersected, if so, return the intersected node
    * 
    * METHOD: first create two pointers (each with counters) at each head and both traverse to the end, see if they are equal.
    *         if so, then there exists an intersection. move the longer linked node with abs(count2 - count1) nodes, 
    *         to align two pointers, then move two pointer together until collide
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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode dummyA = new ListNode(-1, headA);
        ListNode dummyB = new ListNode(-1, headB);
        ListNode posA = dummyA;
        ListNode posB = dummyB;
        int count1 = 0;
        int count2 = 0;
        while (posA != null && posA.next != null) {
            posA = posA.next;
            count1 ++;
        }
        while (posB != null && posB.next != null) {
            posB = posB.next;
            count2 ++;
        }
        if (posA == posB) {
            if (count1 < count2) {
                posA = dummyA;
                posB = dummyB;
                int i = 0;
                while (i ++ < count2 - count1) {
                    posB = posB.next;
                }
                while(posA != posB) {
                    posA = posA.next;
                    posB = posB.next;
                }
                return posA;
            } else {
                posA = dummyA;
                posB = dummyB;
                int i = 0;
                while (i ++ < count1 - count2) {
                    posA = posA.next;
                }
                while(posA != posB) {
                    posA = posA.next;
                    posB = posB.next;
                }
                return posA;
            }
        } else {
            return null;
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
        int[] arr = {1, 2, 3, 4, 5};
        ListNode tail = obj.createLNfromarray(arr);
        ListNode headA = obj.new ListNode(8, tail);
        ListNode headB = obj.new ListNode(7, tail);
        headA = obj.new ListNode(9, headA);
        System.out.println(obj.getIntersectionNode(headA, headB).val);
        }
}