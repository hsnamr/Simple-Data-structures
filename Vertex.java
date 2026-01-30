package ics202;

public interface Vertex extends MyComparable {

    int getNumber();

    Object getWeight();

    Enumeration getIncidentEdges();

    Enumeration getEmanatingEdges();

    Enumeration getPredecessors();

    Enumeration getSuccessors();
}
