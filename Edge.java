package ics202;

public interface Edge extends MyComparable
{
    public abstract Vertex getV0();
    public abstract Vertex getV1();
    public abstract Object getWeight();
    public abstract boolean isDirected();
    public abstract Vertex getMate(Vertex vertex);

}
