package ics202;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphAsListsTest {

    GraphAsLists graph;

    @BeforeEach
    void setUp() {
        graph = new GraphAsLists(4);
    }

    @Test void addVertexIncreasesCount() {
        graph.addVertex(0);
        graph.addVertex(1);
        assertEquals(2, graph.getNumberOfVertices());
    }

    @Test void addEdgeIncreasesEdgeCount() {
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        assertEquals(2, graph.getNumberOfEdges());
    }

    @Test void getVertexReturnsAddedVertex() {
        graph.addVertex(0, "v0");
        var v = graph.getVertex(0);
        assertNotNull(v);
        assertEquals(0, v.getNumber());
        assertEquals("v0", v.getWeight());
    }

    @Test void isEdgeReturnsTrueWhenEdgeExists() {
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addEdge(0, 1);
        assertTrue(graph.isEdge(0, 1));
        assertFalse(graph.isEdge(1, 0));
    }

    @Test void getEdgeReturnsEdge() {
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addEdge(0, 1);
        var edge = graph.getEdge(0, 1);
        assertNotNull(edge);
        assertEquals(0, edge.getV0().getNumber());
        assertEquals(1, edge.getV1().getNumber());
    }

    @Test void purgeClearsGraph() {
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addEdge(0, 1);
        graph.purge();
        assertEquals(0, graph.getNumberOfVertices());
        assertEquals(0, graph.getNumberOfEdges());
    }

    @Test void getVerticesEnumeration() {
        graph.addVertex(0);
        graph.addVertex(1);
        var e = graph.getVertices();
        assertTrue(e.hasMoreElements());
        e.nextElement();
        e.nextElement();
        assertFalse(e.hasMoreElements());
    }
}
