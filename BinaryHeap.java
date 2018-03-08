package ics202;

import java.util.NoSuchElementException;


public class BinaryHeap extends AbstractContainer implements PriorityQueue
{

	protected MyComparable array[];
	  
    public BinaryHeap(int i)
    {
        array = new MyComparable[i + 1];
    }
    
    public BinaryHeap(MyComparable[] comparable) 
    {
    	this(comparable.length);
    	 
    	for(int i = 0; i < comparable.length; i++)
    	   array[i + 1] = comparable[i];
    	   
    	count = comparable.length;
    	
    	buildHeapBottomUp();
	}
	
	private void buildHeapBottomUp()
	{
       for(int i = count / 2; i >= 1; i--)
			percolateDown(i);
	}
	
	/* 
	
	This code of percolateDown appears in the lecture slides on Heaps. IT IS WRONG. The correct version is
	given below. The mistake is in using array.length instead of count and < instead of <=.
	
	Fortunately the voice explanation in the lecture slides is still VALID.
	
	
	private void percolateDown(int hole)
	{
	int minChildIndex;
	MyComparable temp = array[hole];
	while(hole * 2 < array.length)
	{
	minChildIndex = hole * 2;
	if(minChildIndex + 1 < array.length && array[minChildIndex + 1].isLT(array[minChildIndex]))
	minChildIndex++;
	if(array[minChildIndex].isLT(temp))
	array[hole] = array[minChildIndex];
	else
	break;
	
	hole = minChildIndex;
	}
	
	array[hole] = temp;
	}
	
	 */
	
	private void percolateDown(int hole)
	{
		int minChildIndex;
		MyComparable temp = array[hole];
		while(hole * 2 <= count)
		{
			minChildIndex = hole * 2;
			if(minChildIndex + 1 <= count && array[minChildIndex + 1].isLT(array[minChildIndex]))
				minChildIndex++;
			if(array[minChildIndex].isLT(temp))
				array[hole] = array[minChildIndex];
			else
				break;
			
			hole = minChildIndex;
		}
		
		array[hole] = temp;
	}
	
	public void purge()
    {
        while(count > 0) 
            array[count--] = null;
    }

    public void enqueue(MyComparable comparable)
    {
        if(isFull())
            throw new ContainerFullException();
            
        int hole = ++count;
        
        // percolate up via a hole
        while(hole > 1 && array[hole / 2].isGT(comparable))
        {
            array[hole] = array[hole / 2];
            hole = hole / 2 ;
    	}

        array[hole] = comparable;
    }

    public MyComparable findMin()
    {
        if(isEmpty())
            throw new ContainerEmptyException();
        else
            return array[1];
    }

    public MyComparable dequeueMin()
    {
        if(isEmpty())
            throw new ContainerEmptyException();
        MyComparable minItem = array[1];
        array[1] = array[count];
        count--;
        percolateDown(1);
        return minItem;
    }
    
    public boolean isFull()
    {
        return count == array.length - 1;
    }
    
	public Enumeration getEnumeration()
	{
		return new Enumeration() 
		{
			protected int pos = 1;
			
			public boolean hasMoreElements()
			{
				return pos <= count;
			}
			
			public Object nextElement()
			{
				if(pos > count)
					throw new NoSuchElementException();
				else
					return array[pos++];
			} 
		};
	}
	
    protected int compareTo(MyComparable comparable)
    {
        throw new MethodNotImplemented();
    }
}