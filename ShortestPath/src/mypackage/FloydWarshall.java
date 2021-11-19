package mypackage;

public class FloydWarshall {

    private double[][] adjtMatrix;
    private int[][] pred;
    private int size;

    public FloydWarshall() {
    }

    public double[][] floydWarshall() {

        double dist[][] = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (adjtMatrix[i][j] != 0) {
                    dist[i][j] = adjtMatrix[i][j];
                    pred[i][j] = j;
                } else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                    pred[i][j] = j;
                }
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pred[i][j] = pred[i][k];
                    }
                }
            }
        }

        return dist;
    }

    public void calcAndShowShortestPath(Vertex start, Vertex target, Graph graph) {
        double begin, end;
        size = graph.getSize();
        pred = new int[size][size];
        adjtMatrix = new double[size][size];
        int s = start.getName(), t = target.getName();
        String path = String.format("%s", s);

        for (Edge e : graph.getEdges()) {
            adjtMatrix[e.getStartVertex().getName()][e.getTargetVertex().getName()] = e.getWeight();
        }

        begin = System.currentTimeMillis();
        double dist[][] = floydWarshall();
        end = System.currentTimeMillis();

        if (t != s) {
            do {
                s = pred[s][t];
                path += " -> " + s;
            } while (s != t);
        }
        printResults(path, dist[start.getName()][target.getName()], end - begin);
    }

    public void printResults(String path, double cost, double time) {
        if (cost == Double.POSITIVE_INFINITY) {
            System.out.println(" Não existe caminho :(");
        } else {
            System.out.println(" Caminho: " + path);
            System.out.printf(" Custo: %.0f\n", cost);
            System.out.printf(" Tempo: %.3fs\n", time / 1000);
        }
    }

}
