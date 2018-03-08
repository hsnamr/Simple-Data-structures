package ics202.lab01;

import ics202.*;

public class MyContainer extends AbstractContainer {
	private final int SIZE = 100; 
	protected MyComparable[] list = new MyComparable[SIZE];
	
	public boolean isFull() {
		return count == SIZE;
	}
	
	public void purge() {
		for (int i=0; i<count; i++)
			list[i] = null;
		count = 0;
	}
	
	public Enumeration getEnumeration() {
		
		return new Enumeration () {
			
			private int pos = 0;
			
			public boolean hasMoreElements() {
				return pos < count;
			}
			
			public Object nextElement() {
				MyComparable element = list[pos];
				pos++;
				return element;
			}
		};
	}
	
	public int compareTo(MyComparable object) {
		throw new MethodNotImplemented();
	}
	
	public void insert(MyComparable object) {
		if (!isFull()) {
			list[count] = object;
			count++;
		}
	}
}			