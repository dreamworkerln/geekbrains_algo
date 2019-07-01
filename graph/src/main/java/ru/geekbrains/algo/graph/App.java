package ru.geekbrains.algo.graph;

import ru.geekbrains.algo.graph.entities.DijkstrasAlgorithm;
import ru.geekbrains.algo.graph.entities.Graph;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) {

        new App();
    }
    
    

    App() {


        Graph graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Тамбов");
        graph.addVertex("Липецк");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addEdge("Москва","Тула", 182);
        graph.addEdge("Москва","Калуга", 188);
        graph.addEdge("Москва", "Рязань", 202);
        graph.addEdge("Тула","Липецк", 294);
        graph.addEdge("Рязань","Тамбов", 289);
        graph.addEdge("Калуга","Орел", 214);
        graph.addEdge("Липецк","Воронеж", 132);
        graph.addEdge("Тамбов","Саратов", 378);
        graph.addEdge("Орел","Курск", 170);
        graph.addEdge("Саратов","Воронеж", 505);
        graph.addEdge("Курск","Воронеж", 228);

        graph.display();

        DijkstrasAlgorithm.dijkstra(graph.getAdjMat(), 0, 8);

    }

}
