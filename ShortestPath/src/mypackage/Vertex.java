package mypackage;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private final int name;
    private boolean visited;
    private List<Edge> edges;
    private Vertex previosVertex;
    private double minDistance = Double.POSITIVE_INFINITY;

    public Vertex(int name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public int getName() {
        return name;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPreviosVertex() {
        return previosVertex;
    }

    public void setPreviosVertex(Vertex previosVertex) {
        this.previosVertex = previosVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.minDistance, otherVertex.minDistance);
    }
}
