package mypackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertexs;
    private List<Edge> edges;

    public Graph readFromFile(String nomeArquivo) {
        List<Vertex> vertexsList = new ArrayList<>();
        List<Edge> edgesList = new ArrayList<>();
        Vertex currentVertex, targetVertex;
        String fileLine, fileEdge[];
        int vertexsQtd, weight;
        Edge edge;

        try {
            Path currentRelativePath = Paths.get("");
            String url = currentRelativePath.toAbsolutePath().toString()
                    + "\\src\\mypackage\\testfiles\\" + nomeArquivo;
            BufferedReader br = new BufferedReader(new FileReader(url));

            fileEdge = br.readLine().split(" ");
            vertexsQtd = Integer.parseInt(fileEdge[0]);
            for (int i = 0; i < vertexsQtd; i++) {
                vertexsList.add(new Vertex(i));
            }

            while ((fileLine = br.readLine()) != null) {
                fileEdge = fileLine.split(" ");
                currentVertex = vertexsList.get(Integer.parseInt(fileEdge[0]));
                targetVertex = vertexsList.get(Integer.parseInt(fileEdge[1]));
                weight = Integer.parseInt(fileEdge[2]);
                edge = new Edge(currentVertex, targetVertex, weight);
                currentVertex.addEdge(edge);
                edgesList.add(edge);
            }

            this.edges = edgesList;
            this.vertexs = vertexsList;

        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado.");
            return null;
        } catch (IOException e) {
            System.out.println("Houve um erro na função readFromFile.");
            return null;
        }
        return this;
    }

    public List<Vertex> getVertexs() {
        return this.vertexs;
    }

    public void setVertexs(List<Vertex> vertexs) {
        this.vertexs = vertexs;
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getSize() {
        return this.vertexs.size();
    }

}
