package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFord {

    public BellmanFord() {
    }

    public void computePath(Vertex start, List<Edge> edgeList, int size) {
        start.setMinDistance(0);
        for (int i = 0; i < size - 1; i++) {
            for (Edge edge : edgeList) {
                if (edge.getStartVertex().getMinDistance() == Double.POSITIVE_INFINITY) {
                    continue;
                }
                {
                    Vertex v = edge.getStartVertex();
                    Vertex u = edge.getTargetVertex();
                    double newDistance = v.getMinDistance() + edge.getWeight();
                    if (newDistance < u.getMinDistance()) {
                        u.setMinDistance(newDistance);
                        u.setPreviosVertex(v);
                    }
                }
            }
        }
    }

    public void calcAndShowShortestPath(Vertex start, Vertex target, Graph graph) {
        List<Vertex> path = new ArrayList<>();
        double begin, end;

        begin = System.currentTimeMillis();
        computePath(start, graph.getEdges(), graph.getSize());
        end = System.currentTimeMillis();

        for (Vertex vertex = target; vertex != null; vertex = vertex.getPreviosVertex()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        printResults(path, target, end - begin);
    }

    public void printResults(List path, Vertex target, double time) {
        if (target.getMinDistance() == Double.POSITIVE_INFINITY) {
            System.out.println(" Não existe caminho :(");
        } else {
            System.out.printf(" Caminho: " + path.get(0));
            for (int i = 1; i < path.size(); i++) {
                System.out.printf(" -> " + path.get(i));
            }
            System.out.println("");
            System.out.printf(" Custo: %.0f\n", target.getMinDistance());
            System.out.printf(" Tempo: %.3fs\n", time / 1000);
        }
    }

}
