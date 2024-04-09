/**
 * The `linkedList` class in Java provides functionalities to manipulate a
 * singly linked list including
 * adding elements at the beginning, end, or middle, removing elements,
 * searching for elements,
 * reversing the list, deleting the Nth node from the end, and checking if the
 * list is a palindrome.
 * /**
 */
public class linkedList {
    public static class Node {
        int data;
        Node next;

        // constructors
        public Node(int data) {
            this.data = data;
            this.next = null;

        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // add in first in linkedList
    public void addFirst(int data) {
        // step1=create new node
        Node newNode = new Node(data);
        size++;
        // when my linkList is empty
        if (head == null) {
            head = tail = newNode;
            return;
        }
        // step2-newNode next =head
        newNode.next = head;// link

        // step3-head=newNode
        head = newNode;
    }

    // add in last linkedlist
    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // print linkedList
    public void print() {

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // add in middle
    public void addMiddle(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }
        // i=idx-1;temp->prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // remove element from first node
    public int removeFirst() {
        if (size == 0) {
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    // remove element from last element
    public int removeLast() {
        if (size == 0) {
            System.out.println("LL is  empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        // prev: i=size-2
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    // search operation
    public int search(int key) {
        int i = 1;
        Node temp = head;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }

            temp = temp.next;
            i++;
        }
        // key not found
        return -1;
    }

    // search key using recursion method
    public int helper(Node head, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public int recSearch(int key) {
        return helper(head, key);
    }

    // reverse in linkedList
    public void reverse() {// time complexity O(n)
        Node prev = null;
        Node curr = tail = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    // find & Remove Nth node from end
    // it asked by amazon flipkart
    // time complexity O(n)
    public void deleteNthfromEnd(int n) {
        // calculate size
        int sz = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            sz++;
        }
        if (n == sz) {
            head = head.next;// remove first
            return;
        }
        // sz-n
        int i = 1;
        int iToFind = sz - n;
        Node prev = head;
        while (i < iToFind) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
        return;
    }

    // to check if LL is a pailndrom or not
    // slow fast concept
    // reverse half link list
    // slow fast approach
    public Node findMid(Node head) {// helper
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;// +1
            fast = fast.next.next;// +2

        }
        return slow;// slow is my midNode
    }

    public boolean checkPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        // step1-find mid
        Node midNode = findMid(head);

        // step2 - reverse 2nd half
        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;// right half head
        Node left = head;
        // step3 - check left half & right half
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;// mid Node

    }

    private Node merge(Node head1, Node head2) {
        Node mergeLL = new Node(-1);
        Node temp = mergeLL;
        while (head != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next;
            } else {
                temp.next = head2;
                head = head2.next;
                temp = temp.next;
            }
        }
        while (head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }
        while (head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergeLL.next;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // find mid
        Node mid = getMid(head);

        // left and right mergr sort
        Node rightHead = mid.next;
        mid.next = null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);
        // merge
        return merge(newLeft, newRight);
    }

    // Zig-Zag Linked List
    // 1-find mid
    public Node mid(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;// mid
    }
    // 2-reverse mid next half linkedlist
    // public int checkreverse()

    public static void main(String[] args) {
        linkedList li = new linkedList();
        // li.addLast(1);
        // li.addLast(2);
        // li.addLast(2);
        // li.addLast(1);
        // li.addFirst(2);
        // li.addFirst(1);
        // li.addLast(4);
        // li.addLast(5);
        // li.addMiddle(2, 3);
        // li.print();
        // System.out.println(li.size);
        // li.removeFirst();
        // li.print();
        // li.removeLast();
        // li.print();
        // System.out.println(li.size);
        // System.out.println(li.search(4));
        // System.out.println(li.search(10));
        // System.out.println(li.recSearch(3));
        // System.out.println(li.recSearch(10));
        // li.reverse();
        // li.print();
        // li.deleteNthfromEnd(3);
        // li.print();
        // System.out.println(li.checkPalindrome());
        li.addFirst(1);
        li.addFirst(2);
        li.addFirst(3);
        li.addFirst(4);
        li.addFirst(5);
        // 5->4->3->->1
        li.print();
        li.head = li.mergeSort(li.head);
        li.print();
    }
}
