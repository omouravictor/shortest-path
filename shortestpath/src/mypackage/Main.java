package mypackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String file;
        int alg, size, op;
        Graph graph = new Graph();
        Scanner scan = new Scanner(System.in);
        Dijkstra dijkstra = new Dijkstra();
        BellmanFord bellman = new BellmanFord();
        FloydWarshall floyd = new FloydWarshall();

        do {
            file = readFileName(scan);
            if (graph.readFromFile(file) != null) {
                size = graph.getSize();

                alg = readAlgorithm(scan);
                Vertex start = graph.getVertexs().get(readStartVertex(scan, size));
                Vertex target = graph.getVertexs().get(readTargetVertex(scan, size));

                switch (alg) {
                    case 1:
                        System.out.println("Processando Dijkstra em " + file + " ...");
                        dijkstra.calcAndShowShortestPath(start, target);
                        break;
                    case 2:
                        System.out.println("Processando BellmanFord em " + file + " ...");
                        bellman.calcAndShowShortestPath(start, target, graph);
                        break;
                    case 3:
                        System.out.println("Processando FloydWarshall em " + file + " ...");
                        floyd.calcAndShowShortestPath(start, target, graph);
                        break;
                }
            }
            op = readRunAgain(scan);
        } while (op != 2);
    }

    public static String readFileName(Scanner scan) {
        int optionFile = -1;
        do {
            try {
                System.out.println(" 1 - toy.txt");
                System.out.println(" 2 - rg300_4730.txt");
                System.out.println(" 3 - rome99c.txt");
                System.out.println(" 4 - facebook_combined.txt");
                System.out.println(" 5 - USA-road-dt.DC.txt");
                System.out.printf("Informe o arquivo grafo: ");
                optionFile = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor válido.");
                scan.next();
            }
            if (optionFile < 1 || optionFile > 5) {
                System.out.println("Opção inválida.");
            }
        } while (optionFile < 1 || optionFile > 5);

        return getFileName(optionFile);
    }

    public static String getFileName(int optionFile) {
        String file = "";
        switch (optionFile) {
            case 1:
                file = "toy.txt";
                break;
            case 2:
                file = "rg300_4730.txt";
                break;
            case 3:
                file = "rome99c.txt";
                break;
            case 4:
                file = "facebook_combined.txt";
                break;
            case 5:
                file = "USA-road-dt.DC.txt";
                break;
        }
        return file;
    }

    public static int readRunAgain(Scanner scan) {
        int op = -1;
        do {
            try {
                System.out.println(" 1 - Sim");
                System.out.println(" 2 - Não");
                System.out.printf("Executar novamente: ");
                op = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor válido.");
                scan.next();
            }
            if (op != 1 && op != 2) {
                System.out.println("Opção inválida.");
            }
        } while (op != 1 && op != 2);
        return op;
    }

    public static int readAlgorithm(Scanner scan) {
        int alg = -1;
        do {
            try {
                System.out.println(" 1 Dijkstra");
                System.out.println(" 2 Bellman-Ford");
                System.out.println(" 3 Floyd-Warshall");
                System.out.printf("Informe o algoritmo a ser utilizado: ");
                alg = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor válido.");
                scan.next();
            }
            if (alg < 1 || alg > 3) {
                System.out.println("O valor informado não está entre as opções.");
            }
        } while (alg < 1 || alg > 3);
        return alg;
    }

    public static int readStartVertex(Scanner scan, int size) {
        int start = -1;
        do {
            try {
                System.out.printf("Informe o vertice de Origem: ");
                start = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor válido.");
                scan.next();
            }
            if (start < 0 || start >= size) {
                System.out.println("O vértice informado não existe.");
            }
        } while (start < 0 || start >= size);
        return start;
    }

    public static int readTargetVertex(Scanner scan, int size) {
        int target = -1;
        do {
            try {
                System.out.printf("Informe o vertice de Destino: ");
                target = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Informe um valor válido.");
                scan.next();
            }
            if (target < 0 || target >= size) {
                System.out.println("O vértice informado não existe.");
            }
        } while (target < 0 || target >= size);
        return target;
    }
}
