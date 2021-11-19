package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public void computePath(Vertex source) {
        source.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(source);
        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();
            for (Edge edge : vertex.getEdges()) {
                Vertex v = edge.getTargetVertex();
                double weight = edge.getWeight();
                double minDistance = vertex.getMinDistance() + weight;
                if (minDistance < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPreviosVertex(vertex);
                    v.setMinDistance(minDistance);
                    priorityQueue.add(v);
                }
            }
        }
    }

    public void calcAndShowShortestPath(Vertex start, Vertex target) {
        List<Vertex> path = new ArrayList<>();
        double begin, end;

        begin = System.currentTimeMillis();
        computePath(start);
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
