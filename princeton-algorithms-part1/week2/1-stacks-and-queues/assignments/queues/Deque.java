import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private static class Node<Item> {
    public Item data;
    public Node<Item> prev;
    public Node<Item> next;

    public Node(Item data) {
      this.data = data;
    }

    public Node(Item data, Node<Item> prev, Node<Item> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }

  private int size = 0;
  private Node<Item> head = null;
  private Node<Item> tail = null;

  // construct an empty deque
  public Deque() {
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) throw new IllegalArgumentException("Item must not be null");

    if (size == 0) head = tail = new Node<Item>(item);
    else head = head.prev = new Node<Item>(item, null, head);
    ++size;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) throw new IllegalArgumentException("Item must not be null");

    if (size == 0) tail = head = new Node<Item>(item);
    else tail = tail.next = new Node<Item>(item, tail, null);
    ++size;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (size == 0) throw new NoSuchElementException("Deque is empty");

    Item result = head.data;
    if (size == 1) head = tail = null;
    else {
      head = head.next;
      head.prev = null;
    }
    --size;
    return result;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (size == 0) throw new NoSuchElementException("Deque is empty");

    Item result = tail.data;
    if (size == 1) tail = head = null;
    else {
      tail = tail.prev;
      tail.next = null;
    }
    --size;
    return result;
  }

  private class DequeIterator implements Iterator<Item> {
    private Node<Item> cursor = head;

    // Checks if the next element exists
    public boolean hasNext() {
      return cursor != null;
    }

    // moves the cursor/iterator to next element
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item result = cursor.data;
      cursor = cursor.next;
      return result;
    }

    // Used to remove an element. Implement only if needed
    public void remove() {
      throw new UnsupportedOperationException("Remove is not supported");
    }
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> d = new Deque<Integer>();
    d.addFirst(3);
    d.addLast(4);
    d.addFirst(2);
    d.addLast(5);
    d.addFirst(1);
    d.addLast(6);
    d.addLast(0);
    System.out.println(d.removeLast());

    for (Integer item: d) {
      System.out.println(item);
    }
    while (!d.isEmpty()) System.out.println(d.removeFirst());
  }
}
