package LINKED_NODES.basic_op;

class ListNode {
    int val;
    ListNode next;
    ListNode() {};
    ListNode(int val, ListNode l) {this.val = val; this.next = l;};
}

class MyLinkedList {
        /* 
        * TASK:   implement the five basic operations of listnodes
        * 
        * METHOD: Nothing important. Just focus on usage of 'return' when it is the right time.
        * 
        * NOTE:   None
        * 
        * TIME/
        * SPACE:  O(N) / O(1)
        */
    int size;
    ListNode head; // dummy

    public MyLinkedList() {
        this.size = 0;
        this.head = new ListNode(-1, null);
    }
    
    public int get(int index) {
        if (index >= this.size || index < 0) {
            return -1;
        } else {
            ListNode pos = this.head;
            int n = 0;
            while (n <= index) {
                pos = pos.next;
                n ++;
            }
            return pos.val;
        }
    }
    
    public void addAtHead(int val) {
        ListNode tmp = new ListNode(val, this.head.next);
        this.head.next = tmp;
        this.size ++;
    }
    
    public void addAtTail(int val) {
        ListNode pos = this.head;
        while (pos.next != null) {
            pos = pos.next;
        }
        ListNode tmp = new ListNode(val, null);
        pos.next = tmp;
        this.size ++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index == this.size) {
            this.addAtTail(val);
            return; // IMPORTANT !!!
        } else if (index <= 0) {
            this.addAtHead(val);
            return; // IMPORTANT !!!
        } else if (index > this.size) {
            return;
        }
        ListNode pos = this.head;
        int n = 0;
        while (n ++ < index) {
            pos = pos.next;
        }
        ListNode tmp = new ListNode(val, pos.next);
        pos.next = tmp;
        this.size ++;
    }
    
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            return;
        }
        ListNode pos = this.head;
        int n = 0;
        while (n++ < index) {
            pos = pos.next;
        }
        pos.next = pos.next.next;
        this.size --;
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
        MyLinkedList obj = new MyLinkedList();
        obj.addAtHead(7);
        obj.addAtHead(2);
        obj.addAtHead(1);
        obj.addAtIndex(3, 0);
        obj.deleteAtIndex(2);
        obj.addAtHead(6);
        obj.addAtTail(4);
        obj.get(4);
        obj.addAtHead(4);
        obj.addAtIndex(5, 0);
        obj.addAtHead(6);
        obj.printListNodes(obj.head);
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */