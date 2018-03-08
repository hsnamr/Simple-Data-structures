package ics202;

public abstract class AbstractContainer extends	AbstractObject implements Container {
	protected int count;
   
	public int getCount () {
		return count;
	}

	public boolean isEmpty () {
		return getCount () == 0;
	}

	public boolean isFull () {
		return false;
	}

	public void accept(Visitor visitor) {
		Enumeration enumeration = getEnumeration();

		while ( enumeration.hasMoreElements() && !visitor.isDone())
			visitor.visit(enumeration.nextElement());
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		AbstractVisitor visitor = new AbstractVisitor() {
			private boolean comma;
						
			public void visit(Object obj) {
				if(comma)
					buffer.append(", ");
				buffer.append(obj);
				comma = true;
			}
		};

		accept(visitor);
		return "{" + buffer + "}";
	}
	
	public abstract void purge();
	public abstract Enumeration getEnumeration(); 
}
