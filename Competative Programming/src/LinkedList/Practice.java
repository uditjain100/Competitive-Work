package LinkedList;

public class Practice {

    public static class LinkedList {

        public static class Node {
            int val;
            Node next;

            public Node(int v) {
                this.val = v;
            }
        }

        protected Node head;
        protected Node tail;
        protected int size;

        public LinkedList() {
            this.size = 0;
        }

        public int size() {
            return this.size;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override
        public String toString() {
            Node curr = this.head;
            String str = "";
            int idx = 0;
            while (curr.next != null) {
                str += "[" + curr.val + " : " + idx + "]" + " -> ";
                curr = curr.next;
                idx++;
            }
            str += "[" + curr.val + " : " + idx + "]";
            return str;
        }

        public void addFirst(int val) {
            Node nn = new Node(val);
            if (this.isEmpty()) {
                this.head = nn;
                this.tail = nn;
            } else {
                nn.next = this.head;
                this.head = nn;
            }
            this.size++;
        }

        public void addLast(int val) {
            Node nn = new Node(val);
            if (this.isEmpty()) {
                this.head = nn;
                this.tail = nn;
            } else {
                this.tail.next = nn;
                this.tail = nn;
            }
            this.size++;
        }

        public void addAt(int val, int idx) {

            if (idx < 0 || idx > this.size) {
                System.out.println("Index Out of Bound : " + idx);
                return;
            }

            Node nn = new Node(val);
            if (idx == 0) {
                this.addFirst(val);
            } else if (idx == this.size) {
                this.addLast(val);
            } else {
                Node prev = this.getAt(idx - 1);
                Node next = prev.next;
                prev.next = nn;
                nn.next = next;
            }
            this.size++;
        }

        public Node getFirst() {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }
            return this.head;
        }

        public Node getLast() {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }
            return this.tail;
        }

        public Node getAt(int idx) {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }

            if (idx < 0 || idx > this.size) {
                System.out.println("Index Out of Bound : " + idx);
                return null;
            }

            if (idx == 0)
                return this.getFirst();
            else if (idx == this.size)
                return this.getLast();
            else {
                Node curr = this.head;
                while (curr != null && idx-- > 1)
                    curr = curr.next;
                return curr;
            }
        }

        public Node removeFirst() {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }

            Node rn = this.head;
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else
                this.head = this.head.next;
            this.size--;
            return rn;
        }

        public Node removeLast() {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }

            Node rn = this.tail;
            if (this.size == 1) {
                this.head = null;
                this.tail = null;
            } else {
                Node prev = this.getAt(this.size - 1);
                prev.next = null;
                this.tail = prev;
            }
            this.size--;
            return rn;
        }

        public Node removeAt(int idx) {
            if (this.isEmpty()) {
                System.out.println("List is Empty :(");
                return null;
            }

            if (idx < 0 || idx > this.size) {
                System.out.println("Index Out of Bound : " + idx);
                return null;
            }

            if (idx == 0) {
                return this.removeFirst();
            } else if (idx == this.size) {
                return this.removeLast();
            } else {
                Node prev = this.getAt(idx - 1);
                Node rn = prev.next;
                prev.next = rn.next;
                return rn;
            }

        }

    }

    public static class LinkedListAdvance extends LinkedList {

        public Node midPoint() {
            if (this.head == null || this.head.next == null)
                return this.head;

            Node slow = this.head;
            Node fast = this.head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public void reverse() {
            if (this.head == null || this.head.next == null)
                return;

            this.tail = this.head;
            Node prev = null;
            Node curr = this.head;
            Node ahead = curr.next;

            while (ahead != null) {
                curr.next = prev;

                prev = curr;
                curr = ahead;
                ahead = ahead.next;
            }
            curr.next = prev;
            this.head = curr;
        }

        public Node reverse(Node head) {
            if (head == null || head.next == null)
                return head;

            Node prev = null;
            Node curr = head;
            Node ahead = curr.next;

            while (ahead != null) {
                curr.next = prev;

                prev = curr;
                curr = ahead;
                ahead = ahead.next;
            }
            curr.next = prev;
            return curr;
        }

        public void reverseData() {
            if (this.head == null || this.head.next == null)
                return;

            Node curr = this.head;
            Node mp = this.midPoint();
            Node newHead = this.reverse(mp.next);
            mp.next = null;

            Node newCurr = newHead;
            while (curr != null && newCurr != null) {
                int temp = curr.val;
                curr.val = newCurr.val;
                newCurr.val = temp;

                curr = curr.next;
                newCurr = newCurr.next;
            }

            newHead = this.reverse(newHead);
            mp.next = newHead;
        }

        public void swapPairwise() {
            if (this.head == null || this.head.next == null)
                return;

            Node prev = this.head;
            Node curr = prev.next;
            Node ahead = null;

            this.head = this.head.next;

            while (curr != null) {
                prev.next = curr.next;
                curr.next = prev;
                if (ahead != null)
                    ahead.next = curr;
                ahead = prev;

                if (prev.next == null || prev.next.next == null)
                    break;

                prev = prev.next;
                curr = prev.next;
            }
            if (prev.next == null)
                this.tail = prev;
            else
                this.tail = prev.next;
        }

        public void swarPairwiseData() {

            if (this.head == null || this.head.next == null)
                return;

            Node prev = this.head;
            Node curr = prev.next;

            while (prev != null && curr != null) {
                int temp = curr.val;
                curr.val = prev.val;
                prev.val = temp;

                if (curr.next == null || curr.next.next == null)
                    break;

                prev = prev.next.next;
                curr = prev.next;
            }

        }

        public boolean isPalindrome() {
            if (this.head == null || this.head.next == null)
                return true;

            Node curr = this.head;
            Node mp = this.midPoint();
            Node newHead = this.reverse(mp.next);
            mp.next = null;

            Node newCurr = newHead;
            while (curr != null && newCurr != null) {
                if (curr.val != newCurr.val)
                    return false;
                curr = curr.next;
                newCurr = newCurr.next;
            }

            newHead = this.reverse(newHead);
            mp.next = newHead;
            return true;
        }

        public void reOrderList() {

            if (this.head == null || this.head.next == null)
                return;

            Node curr = this.head;
            Node mp = this.midPoint();
            Node newHead = this.reverse(mp.next);
            mp.next = null;

            Node newCurr = newHead;
            while (curr != null && newCurr != null) {
                Node temp1 = curr.next;
                Node temp2 = newCurr.next;
                curr.next = newCurr;
                newCurr.next = temp1;

                this.tail = newCurr;

                curr = temp1;
                newCurr = temp2;
            }

            if (curr != null)
                this.tail = curr;

        }

        public Node mergeLists(Node l1, Node l2) {
            Node head = new Node(-1);
            Node temp = head;

            Node curr1 = l1;
            Node curr2 = l2;

            while (curr1 != null && curr2 != null) {
                if (curr1.val <= curr2.val) {
                    temp.next = curr1;
                    curr1 = curr1.next;
                } else {
                    temp.next = curr2;
                    curr2 = curr2.next;
                }
                temp = temp.next;
            }

            if (curr1 != null)
                temp.next = curr1;

            if (curr2 != null)
                temp.next = curr2;

            return temp.next;

        }

        public void OddEvenList() {

            Node oh = new Node(-1);
            Node eh = new Node(-1);
            Node oc = oh;
            Node ec = eh;

            Node curr = this.head;

            int idx = 0;
            while (curr != null) {
                if (idx % 2 == 1) {
                    oc.next = curr;
                    oc = oc.next;
                } else {
                    ec.next = curr;
                    ec = ec.next;
                }
                idx++;
                curr = curr.next;
            }

            oc.next = eh.next;
            this.head = oh.next;
            this.tail = ec;
        }

        public boolean isCycleExist() {

            if (this.head == null || this.head.next == null)
                return false;

            Node slow = this.head;
            Node fast = this.head;

            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast)
                    return true;
            }

            return false;
        }

        public Node CycleIntersection() {
            if (this.head == null || this.head.next == null)
                return head;

            Node slow = this.head;
            Node fast = this.head;

            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast)
                    break;
            }

            if (slow != fast)
                return null;

            slow = this.head;

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return head;
        }

    }

    public static void main(String[] args) {

        LinkedListAdvance list = new LinkedListAdvance();

        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addFirst(40);
        list.addFirst(50);
        System.out.println(list);

        list.addLast(100);
        list.addLast(200);
        list.addLast(300);
        list.addLast(400);
        list.addLast(500);
        System.out.println(list);

        list.addAt(55, 6);
        System.out.println(list);

        list.removeFirst();
        list.removeLast();
        System.out.println(list);

        list.removeAt(7);
        System.out.println(list);

        System.out.println("Midpoint : " + list.midPoint().val);

        list.removeAt(-25);
        list.removeAt(2);
        System.out.println(list);

        System.out.println("Midpoint : " + list.midPoint().val);

        list.reverse();
        System.out.println(list);

        // list.addFirst(809);
        // System.out.println(list);

        list.swapPairwise();
        System.out.println(list);

        list.reverseData();
        System.out.println(list);

        list.swarPairwiseData();
        System.out.println(list);

        // LinkedListAdvance l = new LinkedListAdvance();
        // l.addFirst(10);
        // l.addFirst(20);
        // l.addFirst(30);
        // l.addFirst(20);
        // l.addFirst(10);
        // System.out.println(l);
        // System.out.println("IsPalinDrome : " + l.isPalindrome());

        list.reOrderList();
        System.out.println(list);

        list.OddEvenList();
        System.out.println(list);

    }

}
