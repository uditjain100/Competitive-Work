package LinkedList;

public class Lecture_01 {

	public static class LinkedList {

		public class Node {
			int data;
			Node next;

			public Node(int data) {
				this.data = data;
			}
		}

		private Node head = null;
		private Node tail = null;
		private int size = 0;

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
			while (curr != null) {
				str += curr.data + " -> ";
				curr = curr.next;
			}
			return str;
		}

		// ADD operation

		private void addFirstNode(Node node) {
			if (isEmpty()) {
				this.head = node;
				this.tail = node;
			} else {
				node.next = this.head;
				this.head = node;
			}
			this.size++;
		}

		public void addFirst(int data) {
			Node node = new Node(data);
			addFirstNode(node);
		}

		private void addLastNode(Node node) {
			if (isEmpty()) {
				this.head = node;
				this.tail = node;
			} else {
				this.tail.next = node;
				this.tail = node;
			}
			this.size++;
		}

		public void addLast(int data) {
			Node node = new Node(data);
			addLastNode(node);
		}

		private void addNodeAt(Node node, int position) throws Exception {
			if (isEmpty() || position == 0) {
				this.addFirst(node.data);
			} else if (position == this.size - 1) {
				this.addLast(node.data);
			} else {
				Node prev = this.getNodeAt(position - 1);
				Node curr = prev.next;
				prev.next = node;
				node.next = curr;
			}
			this.size++;
		}

		public void addAt(int data, int position) throws Exception {
			Node node = new Node(data);
			addNodeAt(node, position);
		}

		// GET operation

		public int getFirst() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			return this.head.data;
		}

		public int getLast() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			return this.tail.data;
		}

		public int getAt(int position) throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			if (position >= this.size) {
				throw new Exception("Null Pointer Exception :(");
			}
			if (position == 0) {
				return this.getFirst();
			} else if (position == this.size - 1) {
				return this.getLast();
			}
			Node curr = this.head;
			for (int i = 0; i < position; i++) {
				curr = curr.next;
			}
			return curr.data;
		}

		public Node getFirstNode() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			return this.head;
		}

		public Node getLastNode() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			return this.tail;
		}

		public Node getNodeAt(int position) throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			if (position >= this.size) {
				throw new Exception("Null Pointer Exception :(");
			}
			if (position == 0) {
				return this.getFirstNode();
			} else if (position == this.size - 1) {
				return this.getLastNode();
			}
			Node curr = this.head;
			for (int i = 0; i < position; i++) {
				curr = curr.next;
			}
			return curr;
		}

		// REMOVE operation

		public int removeFirst() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			Node rn = this.head;
			if (this.size == 1) {
				this.head = null;
				this.tail = null;
			}
			this.head = this.head.next;
			this.size--;
			return rn.data;
		}

		public int removeLast() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			Node prev = getNodeAt(this.size - 2);
			Node rn = this.tail;
			this.tail = prev;
			this.tail.next = null;
			this.size--;
			return rn.data;
		}

		public int removeAt(int position) throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			if (position >= this.size) {
				throw new Exception("Null Pointer Exception :(");
			}
			if (position == 0) {
				return this.removeFirst();
			} else if (position == this.size - 1) {
				return this.removeLast();
			}
			Node prev = getNodeAt(position - 1);
			Node rn = prev.next;
			Node ahead = rn.next;
			prev.next = ahead;
			this.size--;
			return rn.data;
		}

		public Node removeFirstNode() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			Node rn = this.head;
			if (this.size == 1) {
				this.head = null;
				this.tail = null;
			}
			this.head = this.head.next;
			this.size--;
			return rn;
		}

		public Node removeLastNode() throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			Node prev = getNodeAt(this.size - 2);
			Node rn = this.tail;
			this.tail = prev;
			this.tail.next = null;
			this.size--;
			return rn;
		}

		public Node removeNodeAt(int position) throws Exception {
			if (this.isEmpty()) {
				throw new Exception("Linked List is Empty :(");
			}
			if (position >= this.size) {
				throw new Exception("Null Pointer Exception :(");
			}
			if (position == 0) {
				return this.removeFirstNode();
			} else if (position == this.size - 1) {
				return this.removeLastNode();
			}
			Node prev = getNodeAt(position - 1);
			Node rn = prev.next;
			Node ahead = rn.next;
			prev.next = ahead;
			this.size--;
			return rn;
		}

		// Additional functions

		public Node getMidPoint(Node head) {
			Node slow = head;
			Node fast = head;

			while (fast != null && fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			return slow;
		}

		public Node reverseLL(Node head) {

			if (head == null || head.next == null)
				return head;

			Node prev = null;
			Node curr = head;
			Node ahead = curr.next;

			while (curr.next != null) {
				curr.next = prev;

				prev = curr;
				curr = ahead;
				if (ahead != null) {
					ahead = ahead.next;
				}
			}
			curr.next = prev;

			return curr;
		}

		public Node reverseLLData() {

			if (this.head == null || this.head.next == null)
				return this.head;

			Node curr = this.head;
			Node midNode = getMidPoint(this.head);

			Node newHead = midNode.next;
			newHead = this.reverseLL(newHead);
			midNode.next = null;

			Node newCurr = newHead;
			while (curr != null || newCurr != null) {
				int data = curr.data;
				curr.data = newHead.data;
				newHead.data = data;

				curr = curr.next;
				newCurr = newCurr.next;
			}

			newHead = this.reverseLL(newHead);
			midNode.next = newHead;

			return this.head;
		}

		public boolean isPalindrome() {

			if (this.head == null || this.head.next == null)
				return true;

			Node curr = this.head;
			Node midNode = getMidPoint(this.head);
			midNode.next = null;

			Node newHead = midNode.next;
			newHead = this.reverseLL(newHead);

			Node newCurr = newHead;
			while (newCurr != null || curr != null) {
				if (curr.data != newCurr.data)
					return false;

				curr = curr.next;
				newCurr = newCurr.next;
			}

			return true;
		}

		public void reorderList() {

			Node curr = head;
			Node midNode = getMidPoint(head);

			Node newHead = midNode.next;
			newHead = this.reverseLL(newHead);
			midNode.next = null;

			Node newCurr = newHead;
			while (curr != null || newCurr != null) {
				if (curr.next == null) {
					curr.next = newCurr;
					break;
				} else {
					Node temp1 = curr.next;
					Node temp2 = newCurr.next;

					curr.next = newCurr;
					newCurr.next = temp1;

					curr = temp1;
					newCurr = temp2;
				}
			}
		}

		public Node merge2SortedList(Node l1, Node l2) {

			Node head = new Node(-1);
			Node temp = head;

			Node curr1 = l1;
			Node curr2 = l2;

			while (curr1 != null && curr2 != null) {
				if (curr1.data <= curr2.data) {
					temp.next = curr1;
					temp = curr1;
					curr1 = curr1.next;
				} else {
					temp.next = curr2;
					temp = curr2;
					curr2 = curr2.next;
				}
			}

			if (curr1 != null) {
				temp.next = curr1;
			}
			if (curr2 != null) {
				temp.next = curr2;
			}

			return head.next;
		}

		public Node OddEvenLL(Node head) {

			if (head == null || head.next == null)
				return head;

			Node node1 = head;
			Node node2 = head.next;
			Node curr = node2;

			while (node1.next != null && node2.next != null) {
				node1.next = node1.next.next;
				node2.next = node2.next.next;

				node1 = node1.next;
				node2 = node2.next;
			}

			node1.next = curr;
			return head;
		}

		public boolean isCycleExist(Node head) {
			if (head == null || head.next == null)
				return false;

			Node slow = head;
			Node fast = head;

			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (slow == fast)
					return true;
			}

			return false;
		}

		public Node TailCycleIntersection(Node head) {
			if (head == null || head.next == null)
				return null;

			Node slow = head;
			Node fast = head;

			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (slow == fast)
					break;
			}

			if (slow != fast)
				return null;

			slow = head;

			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return slow;
		}

		public Node intersectionOf2Lists(Node head1, Node head2) {

			if (head1 == null || head2 == null)
				return null;
			if (head1.next == null && head2.next == null) {
				if (head1 != head2)
					return null;
				else
					return head1;
			}

			Node curr = head1;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = head2;

			Node slow = head1;
			Node fast = head1;

			while (fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (slow == fast)
					break;
			}

			slow = head1;

			while (slow != fast) {
				slow = slow.next;
				fast = fast.next;
			}
			return slow;
		}

		public Node sortList(Node head) {
			if (head == null || head.next == null)
				return head;

			Node midNode = getMidPoint(head);
			Node newHead = midNode.next;
			midNode.next = null;

			return merge2SortedList(sortList(head), sortList(newHead));
		}

	}

	public static void main(String[] args) throws Exception {
		LinkedList list = new LinkedList();
		for (int i = 1; i <= 5; i++) {
			list.addFirst((-1) * i * 10);
		}
		for (int i = 1; i <= 5; i++) {
			list.addLast(i * 10);
		}
		list.addAt(0, 5);
		System.out.println(list);

		list.reorderList();
		System.out.println(list);
	}
}
