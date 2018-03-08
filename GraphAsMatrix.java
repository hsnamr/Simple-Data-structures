package ics202;

import java.util.NoSuchElementException;

public class GraphAsMatrix extends AbstractGraph {
    protected Edge matrix[][];

    public GraphAsMatrix(int size) {
        super(size);
        matrix = new Edge[size][size];
    }

    protected void addEdge(Edge edge) {
        int v = edge.getV0().getNumber();
        int w = edge.getV1().getNumber();
        if(matrix[v][w] != null)
            throw new InvalidOperationException("duplicate edge");
        if(v == w) {
            throw new InvalidOperationException("loops not allowed");
        } 
        else {
            matrix[v][w] = edge;
            numberOfEdges++;
        }
    }
	
    public Enumeration getEdges() {
        return new Enumeration() {
            protected int v;
            protected int w;

            { //initializer block - finds the first edge
			  search:
                for(v = 0; v < numberOfVertices; v++)
                    for(w = 0; w < numberOfVertices; w++)
                        if(matrix[v][w] != null) 
                           break search;
						
            }

            public boolean hasMoreElements() {
                return v < numberOfVertices && w < numberOfVertices;
            }

            public Object nextElement() {
                if(v >= numberOfVertices || w >= numberOfVertices)
                    throw new NoSuchElementException();
                Edge edge = matrix[v][w];
                for(w++; w < numberOfVertices; w++)
                    if(matrix[v][w] != null)
                        return edge;
				    
                for(v++; v < numberOfVertices; v++)
                    for(w = 0; w < numberOfVertices; w++)
                        if(matrix[v][w] != null)
                            return edge;

               return edge;
            }
        };
    }
	
	public void purge() { //to be implemented by students
	for(int i=0;i<numberOfVertices;numberOfVertices++)
	  for(int j=0;j<numberOfVertices;numberOfVertices++)
	      matrix[i][j]=null;
	      
	      super.purge();
	}
	
	public Edge getEdge(int v, int w) { //to be implemented by students
	   if(v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1)
            throw new IndexOutOfBoundsException();
	     
	     Edge edge=matrix[v][w];
	     return edge;
	}
	
	public boolean isEdge(int v, int w) {  //to be implemented by students
	if(v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1)
			throw new IndexOutOfBoundsException();
			
		Edge edge=matrix[v][w];
		if(v == edge.getV0().getNumber() && w == edge.getV1().getNumber())
		return true;
		else
		return false;
	}
	
	protected Enumeration getEmanatingEdges(int from) { //to be implemented by students
	final int i = from;
	return new Enumeration(){
		int j=0;
		public boolean hasMoreElements(){
			return j<numberOfVertices;
		}
		public Object nextElement(){
			Edge edge=null;
			while(edge!=null){
				 edge=matrix[i][j];
				j++;
				}
				return edge;
			}
		};	
	}
	
	protected Enumeration getIncidentEdges(int to) {  //to be implemented by students
	final int j = to;
	return new Enumeration(){
		int i=0;
		public boolean hasMoreElements(){
			return i<numberOfVertices;
		}
		public Object nextElement(){
			Edge edge=null;
			while(edge!=null){
				 edge=matrix[i][j];
				i++;
				}
				return edge;
			}
		};	
	}
	
    protected int compareTo(MyComparable comparable)  {
        throw new MethodNotImplemented();   // you do not need to implement this.
    }
}
