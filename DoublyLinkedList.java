package ics202;
import java.util.NoSuchElementException;

public class DoublyLinkedList {
	protected Element head;
	protected Element tail;
	
	public void purge() {
        head = null;
        tail = null;
    }
	public Element getHead() {
        return head;
    }

    public Element getTail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Object getFirst() {
        if(head == null)
            throw new ListEmptyException();
        else
            return head.datum;
    }

    public Object getLast() {
        if(tail == null)
            throw new ListEmptyException();
        else
            return tail.datum;
    }
    public void prepend(Object item) {
        Element tmp = new Element(item, head, null);
        if(head != null) {
            tmp.next.previous = tmp;
        	head = tmp;
        } else head = tail = tmp;
    }

    public void append(Object item) {
        Element tmp = new Element(item, null, tail);
        if(head != null) {
            tail.next = tmp;
        	tail = tmp;
        } else head = tail = tmp;
    }

    public void assign(DoublyLinkedList dlist) {
        if(dlist != this) {
            purge();
			for(Element ptr = dlist.head; ptr != null; ptr = ptr.next)
				append(ptr.datum);
        }
    }
    public void extractFirst() {
        if(head == null)
            throw new IllegalArgumentException("item not found");
        head = head.next;
        if(head == null)
            tail = null;
    }

	public void extractLast() {
		if(tail == null)
			throw new IllegalArgumentException("item not found");
		tail.previous.next = null;
		tail = tail.previous;
		if(tail == null)
			head = null;
	}
    
    public String toString() {
		String s = "{";
		Element e = head;
		s += e.datum;
		while (e != tail){
			e = e.next;
			s += ","+e.datum;
		}
		return s+="}";
	}

	public Element find(Object obj) {
		Element element = head;
		if (!isEmpty()){
			while(!element.datum.equals(obj)) {
				element = element.next;
			}
			return element;}
		else
			return null;
	}
	public void extract(Object obj) {
        Element element = head;
        Element lastElement = null;
        while(element != null && ! element.datum.equals(obj)) {
            lastElement = element;
			element = element.next;
		}

        if(element == null)
            throw new IllegalArgumentException("item not found");
        if(element == head)
            head = element.next;
        else
            lastElement.next = element.next;
        if(element == tail)
            tail = lastElement;
    }
    
	public final class Element {
		Element e = head;
		Object datum;
		Element next;
		Element previous;
		Element (Object datum, Element next, Element previous) {
			this.datum = datum;
			this.next = next;
			this.previous = previous;
		}
		public Object getDatum() {
			return datum;
		}
		public Element getNext(){
			return next;
		}
		public Element getPrevious() {
			return previous;
		}
		public void insertAfter(Object obj) {
	        Element element = new Element(obj, e.next, e);
			e.next.previous = element;
			e.next = element;

        }

        public void insertBefore(Object obj) {
            Element element = new Element(obj, e.next, e);
			e.next.previous = element;
			e.next = element;


        }

        public void extract() {
			e.next.previous = e.previous;
			e.previous.next = e.next;
			e.previous = null;
			e.next = null;
        }
	}
}