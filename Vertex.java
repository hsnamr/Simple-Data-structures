package ics202;

public interface Vertex  extends MyComparable
{
    public int getNumber();
    public Object getWeight();
    public Enumeration getIncidentEdges();
    public Enumeration getEmanatingEdges();
    public Enumeration getPredecessors();
    public Enumeration getSuccessors();
}
