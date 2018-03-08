package ics202;

public interface MyComparable {
	boolean isLT (MyComparable object);
	boolean isLE (MyComparable object);
	boolean isGT (MyComparable object);
	boolean isGE (MyComparable object);
	boolean isEQ (MyComparable object);
	boolean isNE (MyComparable object);
	int compare (MyComparable object);
}
