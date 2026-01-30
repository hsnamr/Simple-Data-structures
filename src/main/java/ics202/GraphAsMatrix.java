package ics202;

import java.util.NoSuchElementException;

public class GraphAsMatrix extends AbstractGraph {

    protected Edge[][] matrix;

    public GraphAsMatrix(int size) {
        super(size);
        matrix = new Edge[size][size];
    }

    @Override
    protected void addEdge(Edge edge) {
        int v = edge.getV0().getNumber();
        int w = edge.getV1().getNumber();
        if (matrix[v][w] != null) {
            throw new InvalidOperationException("duplicate edge");
        }
        if (v == w) {
            throw new InvalidOperationException("loops not allowed");
        }
        matrix[v][w] = edge;
        numberOfEdges++;
    }

    @Override
    public Enumeration getEdges() {
        return new Enumeration() {
            private int v;
            private int w;

            {
                search:
                for (v = 0; v < numberOfVertices; v++) {
                    for (w = 0; w < numberOfVertices; w++) {
                        if (matrix[v][w] != null) {
                            break search;
                        }
                    }
                }
            }

            @Override
            public boolean hasMoreElements() {
                return v < numberOfVertices && w < numberOfVertices && matrix[v][w] != null;
            }

            @Override
            public Object nextElement() {
                if (v >= numberOfVertices || w >= numberOfVertices || matrix[v][w] == null) {
                    throw new NoSuchElementException();
                }
                Edge edge = matrix[v][w];
                for (w++; w < numberOfVertices; w++) {
                    if (matrix[v][w] != null) {
                        return edge;
                    }
                }
                for (v++; v < numberOfVertices; v++) {
                    for (w = 0; w < numberOfVertices; w++) {
                        if (matrix[v][w] != null) {
                            return edge;
                        }
                    }
                }
                v = numberOfVertices;
                return edge;
            }
        };
    }

    @Override
    public void purge() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                matrix[i][j] = null;
            }
        }
        super.purge();
    }

    @Override
    public Edge getEdge(int v, int w) {
        if (v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1) {
            throw new IndexOutOfBoundsException();
        }
        return matrix[v][w];
    }

    @Override
    public boolean isEdge(int v, int w) {
        if (v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1) {
            throw new IndexOutOfBoundsException();
        }
        Edge edge = matrix[v][w];
        return edge != null && v == edge.getV0().getNumber() && w == edge.getV1().getNumber();
    }

    @Override
    protected Enumeration getEmanatingEdges(int from) {
        final int i = from;
        return new Enumeration() {
            private int j = 0;

            @Override
            public boolean hasMoreElements() {
                while (j < numberOfVertices && matrix[i][j] == null) {
                    j++;
                }
                return j < numberOfVertices;
            }

            @Override
            public Object nextElement() {
                while (j < numberOfVertices && matrix[i][j] == null) {
                    j++;
                }
                if (j >= numberOfVertices) {
                    throw new NoSuchElementException();
                }
                return matrix[i][j++];
            }
        };
    }

    @Override
    protected Enumeration getIncidentEdges(int to) {
        final int j = to;
        return new Enumeration() {
            private int i = 0;

            @Override
            public boolean hasMoreElements() {
                while (i < numberOfVertices && matrix[i][j] == null) {
                    i++;
                }
                return i < numberOfVertices;
            }

            @Override
            public Object nextElement() {
                while (i < numberOfVertices && matrix[i][j] == null) {
                    i++;
                }
                if (i >= numberOfVertices) {
                    throw new NoSuchElementException();
                }
                return matrix[i++][j];
            }
        };
    }

    @Override
    protected int compareTo(MyComparable comparable) {
        throw new MethodNotImplemented();
    }
}
