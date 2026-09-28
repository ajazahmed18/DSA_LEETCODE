import java.util.*;

class Solution {
    public ListNode middleNode(ListNode head) {
        LinkedList<ListNode> list = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
    return list.get(list.size() / 2);
    }
}