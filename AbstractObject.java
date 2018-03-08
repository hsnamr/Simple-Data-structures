package ics202;

public abstract class AbstractObject implements MyComparable {

	public final boolean isLT (MyComparable object) {
		return compare (object) <  0;
	}
   
    public final boolean isLE (MyComparable object) {
   		return compare (object) <= 0;
   	}
   
    public final boolean isGT (MyComparable object) {
  		return compare (object) >  0;
  	}
 
	public final boolean isGE (MyComparable object) {
  		return compare (object) >= 0;
  	}
  
	public final boolean isEQ (MyComparable object) {
  		return compare (object) == 0;
  	}
 
	public final boolean isNE (MyComparable object) {
		return compare (object) != 0;
	}
  
	public final boolean equals (Object object) {
	 	if (object instanceof MyComparable)
	  	    return isEQ ((MyComparable) object);
	 	else
	  	    return false;
    }
       
	public final int compare (MyComparable arg) {
	   	if (getClass () == arg.getClass ())
	   	    return compareTo (arg);
	  	else
	  	    return getClass ().getName ().compareTo (arg.getClass ().getName ());
	}
	
	protected abstract int compareTo (MyComparable arg);
}
