package ics202;

public interface Edge extends MyComparable {

    Vertex getV0();

    Vertex getV1();

    Object getWeight();

    boolean isDirected();

    Vertex getMate(Vertex vertex);
}
