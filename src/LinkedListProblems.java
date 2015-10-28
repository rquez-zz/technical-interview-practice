import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class LinkedListProblems {

    /*
    Write code to remove duplicates from an unsorted linked list. Solve this problem without using a temporary buffer.
     */
    // O(n^2) time, O(1) space - Solution uses a temporary buffer
    public static Node removeDuplicateNodes(Node head) {
        Node helperFromHead = head;
        while (helperFromHead != null) {
            Node helper = helperFromHead.getNext();
            Node prev = helperFromHead;
            while (helper != null) {
                if (helperFromHead.getData() == helper.getData()) {
                    prev.setNext(helper.getNext());
                    helper.setNext(null);
                    helper = prev.getNext();
                } else {
                    prev = helper;
                    helper = helper.getNext();
                }
            }
            helperFromHead = helperFromHead.getNext();
        }
        return head;
    }
    // O(n) time, O(n) space
    public static Node removeDuplicateNodesHashTable(Node head) {
        HashSet<Integer> hashtable = new HashSet<>();
        hashtable.add(head.getData());
        Node helper = head.getNext();
        Node prev = head;
        while (helper != null) {
            if (hashtable.contains(helper.getData())) {
                prev.setNext(helper.getNext());
                helper.setNext(null);
                helper = prev.getNext();
            } else {
                hashtable.add(helper.getData());
                prev = helper;
                helper = helper.getNext();
            }
        }
        return head;
    }
    // Same as above only recursive
    public static Node removeDuplicateNodesHashTableRecursive(Node head, Node prev, Node current, HashSet<Integer> table) {
        if (current == null) {
            return head;
        }
        if (table.contains(current.getData())) {
            prev.setNext(current.getNext());
            current = prev;
        } else {
            if (head == prev)
                table.add(head.getData());
            table.add(current.getData());
        }
        return removeDuplicateNodesHashTableRecursive(head, current, current.getNext(), table);
    }
    public static class UnitTest {
        Node head;

        @Before
        public void setup() {
            head = new Node(10);
            Node a = new Node(11);
            Node b = new Node(12);
            Node c = new Node(13);
            Node d = new Node(10);
            Node e = new Node(23);
            Node f = new Node(11);
            Node g = new Node(23);
            head.setNext(a);
            a.setNext(b);
            b.setNext(c);
            c.setNext(d);
            d.setNext(e);
            e.setNext(f);
            f.setNext(g);
        }

        @Test
        public void testRemoveDuplicateNodes() {
            Node noDupHead = new Node(10);
            Node a = new Node(11);
            Node b = new Node(12);
            Node c = new Node(13);
            Node e = new Node(23);
            noDupHead.setNext(a);
            a.setNext(b);
            b.setNext(c);
            c.setNext(e);
            Node test = removeDuplicateNodes(head);
            assertTrue(Node.isEqual(noDupHead, test));

            setup();
            test = removeDuplicateNodesHashTable(head);
            assertTrue(Node.isEqual(noDupHead, test));

            setup();
            test = removeDuplicateNodesHashTableRecursive(head, head, head.getNext(), new HashSet<Integer>());
            assertTrue(Node.isEqual(noDupHead, test));
        }
    }
}
