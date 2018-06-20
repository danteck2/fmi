import java.util.Iterator;
import java.util.NoSuchElementException;

public class Coada<Item> implements Iterable<Item>{
    private int n;
    private Nod first;
    private Nod last;
    
    private class Nod {
        private Item item;
        private Nod next;
    }
    public Coada() {
        first = null;
        last  = null;
        n = 0;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return n;     
    }
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("underflow");
        return first.item;
    }
    public Item element() {
        if (isEmpty()) throw new NoSuchElementException("underflow");
        return first.item;
    }
    public void add(Item item) {
        Nod oldlast = last;
        last = new Nod();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }
    public void remove() {
        if (isEmpty()) throw new NoSuchElementException("underflow"); 
        first = first.next;
        n--;
        if (isEmpty()) last = null;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }
    private class ListIterator implements Iterator<Item> {
        private Nod current = first;

        public boolean hasNext()  { 
        	return current != null;                     
        }
        public void remove() { 
        	throw new UnsupportedOperationException();  
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
