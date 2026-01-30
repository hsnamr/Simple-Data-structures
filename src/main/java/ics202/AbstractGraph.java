package ics202;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class AbstractGraph extends AbstractContainer implements Graph {
    protected int numberOfVertices;
    protected int numberOfEdges;
    protected Vertex vertex[];
	
    public AbstractGraph(int size) {
        vertex = new Vertex[size];
    }

	// inner class
    protected final class GraphVertex extends AbstractObject implements Vertex  {
        protected int number;
        protected Object weight;

        public GraphVertex(int v, Object obj) {
            number = v;
            weight = obj;
        }
        
        public GraphVertex(int v) {
            this(v, null);
        }
  
        public int getNumber() {
            return number;
        }

        public Object getWeight() {
            return weight;
        }

		@Override
		protected int compareTo(MyComparable comparable) {
			if (comparable instanceof GraphVertex gv) {
				if (number == gv.getNumber()) {
					return 0;
				} else if (number < gv.getNumber()) {
					return -1;
				} else {
					return +1;
				}
			}
			return getClass().getName().compareTo(comparable.getClass().getName());
		}

        public Enumeration getIncidentEdges() {
            return AbstractGraph.this.getIncidentEdges(number);
        }

        public Enumeration getEmanatingEdges() {
            return AbstractGraph.this.getEmanatingEdges(number);
        }

        public Enumeration getPredecessors() {
            return new Enumeration() {
                Enumeration edges = getIncidentEdges();

                public boolean hasMoreElements() {
                    return edges.hasMoreElements();
                }

                public Object nextElement() {
                    Edge edge = (Edge)edges.nextElement();
                    return edge.getMate(GraphVertex.this);
                }
            };
        }		
		public Enumeration getSuccessors() {
			//To be implemented by Students
			return new Enumeration() {
				Enumeration edges = getEmanatingEdges();
				
				public boolean hasMoreElements() {
					return edges.hasMoreElements();
				}
				
				public Object nextElement()  {
					Edge edge = (Edge)edges.nextElement();
					return edge.getMate(GraphVertex.this);
				}
			};
		}
		
		
		
		@Override
		public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Vertex {").append(number);
            if (weight != null) {
                sb.append(", weight = ").append(weight);
            }
            sb.append("}");
            return sb.toString();
        }
	}
	// inner class
    protected final class GraphEdge extends AbstractObject implements Edge {
        protected int v0;
        protected int v1;
        protected Object weight;

        public GraphEdge(int v, int w, Object obj) {
            v0 = v;
            v1 = w;
            weight = obj;
        }

        public GraphEdge(int v, int w) {
            this(v, w, null);
        }

        public Vertex getV0() {
            return vertex[v0];
        }

        public Vertex getV1() {
            return vertex[v1];
        }

        public Object getWeight() {
            return weight;
        }

        public Vertex getMate(Vertex v) {
            if(v.getNumber() == v0)
                return vertex[v1];
            if(v.getNumber() == v1)
                return vertex[v0];
            else
                throw new InvalidOperationException("invalid vertex");
        }

        public boolean isDirected() {
            return AbstractGraph.this.isDirected();
        }

		@Override
		protected int compareTo(MyComparable comparable) {
			if (comparable instanceof GraphEdge edge) {
				if (v0 < edge.getV0().getNumber()) {
					return -1;
				} else if (v0 > edge.getV0().getNumber()) {
					return +1;
				} else if (v1 < edge.getV1().getNumber()) {
					return -1;
				} else if (v1 > edge.getV1().getNumber()) {
					return +1;
				} else {
					return 0;
				}
			}
			return getClass().getName().compareTo(comparable.getClass().getName());
		}
		
		@Override
		public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Edge {").append(v0);
            sb.append(isDirected() ? "->" : "--").append(v1);
            if (weight != null) {
                sb.append(", weight = ").append(weight);
            }
            sb.append("}");
            return sb.toString();
        }
    }

 // These are methods of AbstractGraph
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void purge() {
        for(int v = 0; v < numberOfVertices; v++)
            vertex[v] = null;

        numberOfVertices = 0;
        numberOfEdges = 0;
    }

	protected void addVertex(Vertex v) {
		//To be implemented by Students
		if(numberOfVertices == vertex.length)
			throw new ContainerFullException();
		if(v.getNumber() != numberOfVertices) {
			throw new InvalidOperationException("invalid vertex number");
		}
		else {
			vertex[numberOfVertices] = v;
			numberOfVertices++;
		}
	}
	
	public final void addVertex(int v, Object obj) {
        addVertex(new GraphVertex(v, obj));
    }

    public final void addVertex(int v) {
        addVertex(v, null);
    }

    public Vertex getVertex(int v) {
       if(v < 0 || v > numberOfVertices - 1)
            throw new IndexOutOfBoundsException();
       return vertex[v];
    }

    public Enumeration getVertices()  {
        return new Enumeration() {
           protected int v;

           public boolean hasMoreElements() {
                return v < numberOfVertices;
            }

            public Object nextElement()   {
                if(v >= numberOfVertices)
                    throw new NoSuchElementException();
                else
                    return vertex[v++];
            }
        };
    }

    public boolean isDirected() {
        return this instanceof Digraph;
    }

    public final void addEdge(int v, int w, Object obj)  {
        addEdge(new GraphEdge(v, w, obj));
    }

    public final void addEdge(int v, int w) {
        addEdge(v, w, null);
    }

    public void depthFirstTraversal(PrePostVisitor visitor, int start) {
        var visited = new boolean[numberOfVertices];
        Arrays.fill(visited, false);
        depthFirstTraversal(visitor, vertex[start], visited);
    }

    private void depthFirstTraversal(PrePostVisitor visitor, Vertex v, boolean[] visited){
        if(visitor.isDone())
            return;
        visitor.preVisit(v);
        visited[v.getNumber()] = true;
        
        Enumeration p = v.getSuccessors();
		while(p.hasMoreElements())    {
            Vertex to = (Vertex) p.nextElement();
            if(! visited[to.getNumber()])
                depthFirstTraversal(visitor, to, visited);
        }
        visitor.postVisit(v);
    }

    public void breadthFirstTraversal(Visitor visitor, int start)  {
        boolean enqueued[] = new boolean[numberOfVertices];
        for(int v = 0; v < numberOfVertices; v++)
            enqueued[v] = false;

        Queue queue = new QueueAsLinkedList();
        enqueued[start] = true;
        queue.enqueue(vertex[start]);
        
        while(!queue.isEmpty() && !visitor.isDone())  {
            Vertex v = (Vertex) queue.dequeue();
            visitor.visit(v);
            Enumeration p = v.getSuccessors();
            
            while(p.hasMoreElements()) {
                Vertex to = (Vertex) p.nextElement();
                if(!enqueued[to.getNumber()]) {
                    enqueued[to.getNumber()] = true;
                    queue.enqueue(to);
				}
            }
        }
    }

    public Enumeration getEnumeration()  {
        return getVertices();
    }

    @Override
    public String toString() {
        var buffer = new StringBuilder();
        accept(forEach(obj -> {
            if (obj instanceof Vertex v) {
                buffer.append(v).append("\n");
                var p = v.getEmanatingEdges();
                while (p.hasMoreElements())
                    buffer.append("    ").append(p.nextElement()).append("\n");
            }
        }));
        return "{" + buffer + "}";
    }

//	Additional Methods
	public void topologicalOrderTraversal(Visitor visitor) {
		var inDegree = new int[numberOfVertices];
		Arrays.fill(inDegree, 0);
		var p = getEdges();
		while (p.hasMoreElements()) {
			Edge edge = (Edge) p.nextElement();
			Vertex to = edge.getV1();
			inDegree[to.getNumber()]++;
		}
		
		Queue queue = new QueueAsLinkedList();
		for(int v = 0; v < numberOfVertices; v++)
			if(inDegree[v] == 0)
				queue.enqueue(vertex[v]);
		
		while(!queue.isEmpty() && !visitor.isDone()) {
			Vertex v = (Vertex)queue.dequeue();
			visitor.visit(v);
			Enumeration q = v.getSuccessors(); 
			while (q.hasMoreElements())
			{
				Vertex to = (Vertex) q.nextElement();
				if(--inDegree[to.getNumber()] == 0)
					queue.enqueue(to);
			}
		}
	}
	
	public int countPredecessors(int targetVertexNumber)  {
			if(targetVertexNumber < 0 || targetVertexNumber > numberOfVertices - 1)
            throw new IndexOutOfBoundsException();
			int count=0;		
			Enumeration p = getEdges();
			while (p.hasMoreElements()) {
				Edge edge = (Edge) p.nextElement();			
				if (targetVertexNumber== edge.getV1().getNumber())
				count++;			
			}
			return count;
	}
	
	
	public boolean isConnected() {
		CountingVisitor visitor = new CountingVisitor();
		breadthFirstTraversal(visitor, 0);
		return visitor.getCount() == numberOfVertices;
	}
	
	public boolean isStronglyConnected() {
		for(int v = 0; v < numberOfVertices; v++) {
			CountingVisitor visitor = new CountingVisitor();
			breadthFirstTraversal(visitor, v);
			if(visitor.getCount() != numberOfVertices)
				return false;
		}
		return true;
	}
	
	public boolean isCyclic()  {
		CountingVisitor visitor = new CountingVisitor();
		topologicalOrderTraversal(visitor);
		return visitor.getCount() != numberOfVertices;
	}

    protected abstract Enumeration getIncidentEdges(int i);
    protected abstract Enumeration getEmanatingEdges(int i);
    protected abstract void addEdge(Edge edge);
    public abstract Edge getEdge(int i, int j);
    public abstract boolean isEdge(int i, int j);
    public abstract Enumeration getEdges();

// other methods	
	public int countComponents() {
		
		boolean[] visited = new boolean[numberOfVertices];
		
		for (int v=0; v<numberOfVertices; v++)
			visited[v] = false;
		
		PrePostVisitor visitor = new AbstractPrePostVisitor() {public void preVisit(Object o) {
			System.out.print(o+" ");}};
		
		int count = 0;
		
		for (int v = 0; v< numberOfVertices; v++) {
			if (!visited[v]) {
//				System.out.print(v+" ");
				depthFirstTraversal(visitor, vertex[v], visited);
				System.out.println();
				count++;
			}
		}
		return count;
	}
	
	public boolean isComplete() {
		for (int v=0; v<numberOfVertices; v++) 
			for (int w=0; w<numberOfVertices; w++) 
				if(v != w && ! isEdge(v,w))
					return false;
	    return true;
	}
			
	public boolean isASink(int v) {
		for (int w=0; w<numberOfVertices; w++)
			if (w != v && (!isEdge(w, v) || isEdge(v,w)))
				return false;
		return true;
	}
	public int countTraversals() {
		var visited = new boolean[numberOfVertices];
		Arrays.fill(visited, false);
		PrintingVisitor pv=new PrintingVisitor();
		PrePostVisitor visitor = new PreOrder(pv);
		int count=0;
		for (int v=0; v<numberOfVertices; v++){
			if(!visited[v])
			{
				depthFirstTraversal(visitor, vertex[v], visited);
				count++;
				System.out.println();
			}
		}
		return count;
	}
	public boolean isReachable(int v, int w) {
		if (v < 0 || v > numberOfVertices - 1 || w < 0 || w > numberOfVertices - 1)
			throw new IndexOutOfBoundsException();
		var vertexV = new GraphVertex(v);
		var vertexW = new GraphVertex(w);
		var visited = new boolean[numberOfVertices];
		Arrays.fill(visited, false);
		var mv = new MatchingVisitor(vertexW);
		var visitor = new PreOrder(mv);
		depthFirstTraversal(visitor, vertexV, visited);
        return mv.isFound();
	}
}
