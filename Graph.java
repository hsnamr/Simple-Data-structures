package ics202;

public interface Graph extends Container
{

    public int getNumberOfEdges();
    public int getNumberOfVertices();
    public boolean isDirected();
    public void addVertex(int v);
    public void addVertex(int v, Object obj);
    public Vertex getVertex(int v);
    public void addEdge(int v, int w);
    public void addEdge(int v, int w, Object obj);
    public Edge getEdge(int v, int w);
    public boolean isEdge(int v, int w);
    public boolean isConnected();
    public boolean isCyclic();
    public Enumeration getVertices();
    public Enumeration getEdges();
    public void depthFirstTraversal(PrePostVisitor prepostvisitor, int start);
    public void breadthFirstTraversal(Visitor visitor, int start);
}
