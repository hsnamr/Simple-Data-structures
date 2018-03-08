package ics202;

import java.util.NoSuchElementException;

public class StackAsLinkedList extends AbstractContainer implements Stack
{
	protected MyLinkedList list;
	
    public StackAsLinkedList()
    {
        list = new MyLinkedList();
    }

    public void purge()
    {
        list.purge();
        count = 0;
    }

    public void push(Object obj)
    {
        list.prepend(obj);
        count++;
    }

    public Object pop()
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

    public Object getTop()
    {
        if(count == 0)
            throw new ContainerEmptyException();
        else
            return list.getFirst();
    }

    public Enumeration getEnumeration()
    {
        return new Enumeration() {
        	
        	protected MyLinkedList.Element position = list.getHead();

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