package ics202;

public interface Container extends MyComparable {
	int getCount ();
	boolean isEmpty ();
	boolean isFull ();
	void purge ();
	void accept (Visitor visitor);
	Enumeration getEnumeration ();
}