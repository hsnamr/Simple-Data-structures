package ics202.lab08;
import ics202.*;

public class MyAssociation extends AbstractObject
{
    
    private class Value
    {
    	int count;
    	MyLinkedList linkedlist = new MyLinkedList();
    }
    
    protected MyComparable key;
    protected Value value;

    public MyAssociation(MyComparable comparable, int lineNumber)
    {
        key = comparable;
        value = new Value();
        value.count = 1;
        value.linkedlist.append(new Int(lineNumber));
    }

    public MyAssociation(MyComparable comparable)
    {
        this(comparable, 0);
    }

    public MyComparable getKey()
    {
        return key;
    }

    public Value getValue()
    {
        return value;
    }
    
    public int getLastLineNumber()
    {
    	return ((Int) value.linkedlist.getLast()).intValue ();
    }
    
    public void incrementWordCount()
    {
    	value.count++;
    }
    
    public void prependLineNumber(int lineNumber)
    {
    	value.linkedlist.append(new Int(lineNumber));
    	value.count++;
    }

    protected int compareTo(MyComparable comparable)
    {
        MyAssociation association = (MyAssociation)comparable;
        return key.compare(association.getKey());
    }

    public String toString()
    {
        String s = "" + key;
        if(value != null)
            s = s + "  " + "Count = " + value.count + " Line numbers: " + value.linkedlist + "\n";
        return s;
    }
}
