package Logic;

public class LinkedList<T> {
	public static class Node<T> {
		T value;
		Node<T> next;
		public Node(T value) {
			this.value = value;
		}
	}
	Node<T> first = null;
	public void addAtFront(Node<T> node) {
		node.next = first;
		first = node;
	}
	public void addAtEnd(Node<T> node) {
		if (first == null) {
			first = node;
		} else {
			Node<T> ptr = first;
			while(ptr.next != null) {
				ptr = ptr.next;
			}
			ptr.next = node;
		}
	}
	public void removeFront() {
		first = first.next;
	}
	public void print() {
		Node<T> ptr = first;
		while(ptr != null) {
			System.out.print(ptr.value + " ->");
			ptr = ptr.next;
		}
	}
}

