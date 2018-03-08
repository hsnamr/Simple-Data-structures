package ics202;

import java.util.NoSuchElementException;

public class QueueAsLinkedList extends AbstractContainer implements Queue
{
	protected MyLinkedList list;
	
    public QueueAsLinkedList()
    {
        list = new MyLinkedList();
    }

    public void purge()
    {
        list.purge();
        count = 0;
    }

    public Object getHead()
    {
        if(count == 0)
            throw new ContainerEmptyException();
        else
            return list.getFirst();
    }

    public void enqueue(Object obj)
    {
        list.append(obj);
        count++;
    }

    public Object dequeue()
    {
        if(count == 0)
            throw new ContainerEmptyException();
        else
        {
            Object obj = list.getFirst();
            list.extractFirst();
            count--;
            return obj;
        }
    }

    public Enumeration getEnumeration()
    {
        return new Enumeration() {
        	
        	MyLinkedList.Element position = list.getHead();

            public boolean hasMoreElements()
            {
                return position != null;
            }

            public Object nextElement()
            {
                if(position == null)
                    throw new NoSuchElementException();
                else
                {
                    Object obj = position.getDatum();
                    position = position.getNext();
                    return obj;
                }
            }
            
        };
    }

    protected int compareTo(MyComparable comparable)
    {
        throw new MethodNotImplemented();
    }
}