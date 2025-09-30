import java.util.Stack;

// Define the ListNode class if it's not already defined
class ListNode {
    int val;                      //integer field to store the value of the node
    ListNode next;                // Pointer to the next node
    ListNode(int x) { val = x; }  // assign x to val field
}
class ReverseLinkedList2 {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        ListNode newCur = new ListNode(stack.pop());
        ListNode newHead = newCur;
        while (!stack.isEmpty()) {
            newCur.next = new ListNode(stack.pop());
            newCur = newCur.next;
        }
        return newHead;
    }
    
}