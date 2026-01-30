package ics202;

public interface Graph extends Container {

    int getNumberOfEdges();

    int getNumberOfVertices();

    boolean isDirected();

    void addVertex(int v);

    void addVertex(int v, Object obj);

    Vertex getVertex(int v);

    void addEdge(int v, int w);

    void addEdge(int v, int w, Object obj);

    Edge getEdge(int v, int w);

    boolean isEdge(int v, int w);

    boolean isConnected();

    boolean isCyclic();

    Enumeration getVertices();

    Enumeration getEdges();

    void depthFirstTraversal(PrePostVisitor prepostvisitor, int start);

    void breadthFirstTraversal(Visitor visitor, int start);
}
