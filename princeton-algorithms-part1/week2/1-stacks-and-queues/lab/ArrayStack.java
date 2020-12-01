import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayStack<T> implements Stack<T> {
  private static final int MIN_INITIAL_SIZE = 8;
  private int cursor = 0;
  private T[] s = (T[]) new Object[MIN_INITIAL_SIZE];

  public ArrayStack() {}

  public boolean isEmpty() {
    return cursor == 0;
  }

  public int size() {
    return cursor;
  }

  public void push(T item) {
    if (item == null) throw new IllegalArgumentException("Item must not be null");

    s[cursor++] = item;
    increaseCapacityIfNeeded();
  }

  public T pop() {
    if (cursor == 0) throw new NoSuchElementException("Stack is empty");

    cursor--;
    T result = s[cursor];
    s[cursor] = null;
    decreaseCapacityIfNeeded();
    return result;
  }

  private void increaseCapacityIfNeeded() {
    if (cursor < s.length) return;

    int newCapacity = s.length << 1;
    if (newCapacity < 0) throw new IllegalStateException("Sorry, stack too big");
    Object[] a = new Object[newCapacity];
    System.arraycopy(s, 0, a, 0, cursor);
    s = (T[]) a;
  }

  private void decreaseCapacityIfNeeded() {
    if (cursor >= s.length / 4) return;

    int newCapacity = s.length >> 1;
    if (newCapacity < MIN_INITIAL_SIZE) return;
    Object[] a = new Object[newCapacity];
    System.arraycopy(s, 0, a, 0, cursor);
    s = (T[]) a;
  }

  private class StackIterator implements Iterator<T> {
    private int it = cursor;

    public boolean hasNext() {
      return it > 0;
    }

    public T next() {
      if (!hasNext()) throw new NoSuchElementException();
      return s[--it];
    }

    public void remove() {
      throw new UnsupportedOperationException("Remove is not supported");
    }
  }

  public Iterator<T> iterator() {
    return new StackIterator();
  }

  public static void main(String[] args) {
    Stack<Integer> s = new ArrayStack<Integer>();
    for (int i = 0; i <= 20; ++i) s.push(i);
    for (Integer num: s) System.out.println(num);
    while (!s.isEmpty()) System.out.println(s.pop());
  }
}
