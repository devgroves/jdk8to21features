package DataStructures;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.*;
import java.util.Map.Entry;

// solution


class UnionFind {
    public UnionFind(int n) {
    }

    public boolean hasCycle(HashMap<Integer, List<Integer>> adjacencyList) {
        // fill in
        // with solution
        System.out.println("adjacency list:" + adjacencyList);
        LinkedList<Integer> cycleDetector = new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> listEntry :adjacencyList.entrySet()) {
            Integer val = listEntry.getKey();
            if (!cycleDetector.contains(val)) {
                cycleDetector.add(val);
            } else {
                return true;
            }

            for (Integer mapValue: listEntry.getValue()) {
                if (!cycleDetector.contains(val)) {
                    cycleDetector.add(val);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void union(int i, int j) {
        // fill in
        // with solution
    }

    public int find(int i) {
        // fill in
        // with solution
        return 0;
    }
}


// available data structures

class Graph {
    HashMap<Integer, List<Integer>> adjacencyList;
    int verticesCount;

    public Graph() {
        this.adjacencyList = new HashMap<Integer, List<Integer>>();
        this.verticesCount = 0;
    }

    public void addVertex(Integer nodeVal) {
        this.adjacencyList.put(nodeVal, new ArrayList<Integer>());
        this.verticesCount++;
    }

    public void addEdge(Integer src, Integer dest) {
        this.adjacencyList.get(src).add(dest);
        this.adjacencyList.get(dest).add(src);
        // push to both adjacency lists
    }

    public void removeVertex(Integer val) {
        if (this.adjacencyList.containsKey(val)) {
            this.adjacencyList.remove(val);

            for (List<Integer> vertex : this.adjacencyList.values()) {
                int neighborIdx = vertex.indexOf(val);
                if (neighborIdx >= 0) {
                    vertex.remove(neighborIdx);
                }
            }
        }
    }

    public void removeEdge(Integer src, Integer dest) {
        int srcDestIdx = this.adjacencyList.get(src).indexOf(dest);
        this.adjacencyList.get(src).remove(srcDestIdx);

        int destSrcIdx = this.adjacencyList.get(dest).indexOf(src);
        this.adjacencyList.get(dest).remove(destSrcIdx);
    }

    public List<String> printNeighbors() {
        List<String> result = new ArrayList<String>();

        StringBuffer buffer = new StringBuffer();
        for (Object vertex : this.adjacencyList.keySet()) {
            buffer.append(vertex.toString());
            buffer.append(':');

            for (Object neighbor : this.adjacencyList.get(vertex)) {
                buffer.append(' ');
                buffer.append(neighbor.toString());
            }

            result.add(buffer.toString());
            buffer.delete(0, buffer.length());
        }

        return result;
    }

    public int getVerticesCount() {
        return this.verticesCount;
    }

    public Graph reverse() {
        Graph graph = new Graph();
        for (Integer src : this.adjacencyList.keySet()) {
            graph.addVertex(src);
        }

        for (Entry<Integer, List<Integer>> entry : this.adjacencyList.entrySet()) {
            Integer src = entry.getKey();
            for (Integer dest : this.adjacencyList.get(src)) {
                graph.adjacencyList.get(src).add(dest);
            }
        }

        return graph;
    }
}

public class DetectCycleGraph {

    int[][] matrix1 = {
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 1, 0, 1, 0},
            {1, 0, 0, 0, 0},
    };

    int[][] matrix2 = {
            {1, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {1, 0, 0, 0},
    };

    char[][] planeMatrix1 = {
            {'.', '.', '.', 'P'},
            {'.', '.', '.', 'P'},
            {'P', 'P', '.', 'P'},
            {'.', '.', '.', 'P'},
    };


    @Test
    public void canDetectCycleInGraphTest() {
        Graph graph = new Graph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        UnionFind unionFind = new UnionFind(graph.adjacencyList.size());
        System.out.println("has cycle : " + unionFind.hasCycle(graph.adjacencyList));
        assertTrue(unionFind.hasCycle(graph.adjacencyList));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DetectCycleGraph.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

//    public static void log() {
//        Graph graph = new Graph();
//        UnionFind unionFind = new UnionFind(graph.adjacencyList.size());
//        System.out.println(unionFind.hasCycle(graph.adjacencyList));
//    }

}

