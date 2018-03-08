package ics202;

import java.util.NoSuchElementException;

public class GraphAsLists extends AbstractGraph {
    protected MyLinkedList adjacencyList[];

    public GraphAsLists(int size) {
        super(size);
        adjacencyList = new MyLinkedList[size];
        for(int v = 0; v < size; v++)
            adjacencyList[v] = new MyLinkedList();
    }

    public void purge()  {
        for(int v = 0; v < numberOfVertices; v++)
            adjacencyList[v].purge();
        super.purge();
    }

    protected void addEdge(Edge edge)  {
        int v = edge.getV0().getNumber();
        adjacencyList[v].append(edge);
        numberOfEdges++;
    }

    public Edge getEdge(int v, int w) {
        if(v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1)
            throw new IndexOutOfBoundsException();
        
        MyLinkedList.Element element = adjacencyList[v].getHead();     
        while(element != null)
        {
            Edge edge = (Edge)element.getDatum();
            if(edge.getV1().getNumber() == w)
                return edge;
            element = element.getNext();     
        }
        throw new IllegalArgumentException("edge not found");
    }

    public boolean isEdge(int v, int w) {
		//To be implemented by Students
		if(v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1)
			throw new IndexOutOfBoundsException();
		
		MyLinkedList.Element element = adjacencyList[v].getHead();     
		while(element != null)
		{
			Edge edge = (Edge)element.getDatum();
			if(edge.getV1().getNumber() == w)
				return true;
			element = element.getNext();     
		}
		return false;
	}


    public Enumeration getEdges() {
		//To be implemented by Students
		return new Enumeration() {
			protected int v;
			MyLinkedList.Element ptr;
			
			{//initializer block - finds the first edge
				for(v = 0; v < numberOfVertices; v++) {
					ptr = adjacencyList[v].getHead();
					if(ptr != null)
						break;
				}
			}
			
			public boolean hasMoreElements() {
				return ptr != null;
			}
			
			public Object nextElement()  {
				if(ptr == null)
					throw new NoSuchElementException();
				Object obj = ptr.getDatum();
				ptr = ptr.getNext();
				if(ptr == null)
					for(v++; v < numberOfVertices; v++)
					{
						ptr = adjacencyList[v].getHead();
						if(ptr != null)
							break;
					}
				return obj;
			}
			
		};
	}

    protected Enumeration getEmanatingEdges(int from) {
        final int v = from;
        return new Enumeration() {
            protected MyLinkedList.Element ptr = adjacencyList[v].getHead();

            public boolean hasMoreElements() {
                return ptr != null;
            }

            public Object nextElement() {
                if(ptr == null) {
                    throw new NoSuchElementException();
                }
                else {
                    Object obj = ptr.getDatum();
                    ptr = ptr.getNext();
                    return obj;
                }
            }
        };
    }

    protected Enumeration getIncidentEdges(int to) {
        throw new MethodNotImplemented();
    }

    protected int compareTo(MyComparable comparable) {
        throw new MethodNotImplemented();
    }
}
