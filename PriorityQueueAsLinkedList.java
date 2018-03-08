package ics202.lab04;

import ics202.*; 
import ics202.MyLinkedList;	
public class PriorityQueueAsLinkedList extends QueueAsLinkedList
{
  	Object ob;
  	
  	boolean status=true;
  	int b;
    public void enqueue(Object obj)
    {
		if(list.isEmpty())
		{
			list.prepend(obj);
		}
		else{
			MyLinkedList.Element x=list.getHead();
			while(x!=null&& ((MyComparable)x.getDatum()).isGE((MyComparable)obj)){
				x=x.getNext();
			}
			if(x==null){
				list.append(obj);
			}
			x.insertBefore(obj);
		}
	}
}				
	
		
		
		
		
		
 