
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode next = null;
            ListNode curr = head; // set current pointer to the head node
            
            while (curr != null) { // while the current pointer is not NULL
                
                next = curr.next; // set the next pointer to the next node in the list
                
                curr.next = prev; // set current node's pointer to point to the previous node  (reverse)
                
                prev = curr; //set prev to value of current
            
                curr = next;// move the current pointer to the next node
            }
            // redundant, returning prev at this point is sufficient - dont need to update head
            // head = prev; // set the head pointer to the last node, which is new head
            // return head;
            return prev; // prev is now the new head of the reversed list
        }
        
    public static void main(String[] args) {

        // Create a linked list
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseLinkedList bracelet = new ReverseLinkedList();
        ListNode reversedHead = bracelet.reverseList(head);

        // Print the reversed linked list
        while (reversedHead != null) {
            System.out.print(reversedHead.val + " ");
            reversedHead = reversedHead.next;  // move to the next node
        }
        System.out.println(":)"); // smiley face
    }
}

