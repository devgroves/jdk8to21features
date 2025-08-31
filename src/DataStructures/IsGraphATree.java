package DataStructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsGraphATree {
    public static boolean isGraphTree(int n, int[][] edges) {
        // fill in with solution..
        HashMap<Integer, Set<Integer>> edgesMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int e1 = edges[i][0];
            int e2 = edges[i][1];
            if (e1 == e2) return false; // node connecting to same node.. 
            Set<Integer> edgeSet = null;
            if (edgesMap.get(e1) == null) {
                edgeSet = new HashSet<>();
                edgeSet.add(e2);
                edgesMap.put(e1, edgeSet);
            } else {
                edgeSet = edgesMap.get(e1);
                edgeSet.add(e2);
            }
            boolean result = addToPreviousEdges(edgesMap, e1, e2);
            if (result == false) return result;
        }
        return false;
    }

    public static boolean addToPreviousEdges(HashMap<Integer, Set<Integer>> edgesMap, int e1, int e2) {
        boolean result = true;
        for (Map.Entry<Integer, Set<Integer>> entry : edgesMap.entrySet()) {
            if (entry.getValue().contains(e1)) {
                result = entry.getValue().add(e2);
                if (!result) break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isGraphTree(3, new int[][] {{1, 3}, {2, 3}, {1, 2}}));
        System.out.println(isGraphTree(4, new int[][] {{1, 2}, {1, 3}, {3, 4}, {4, 1}}));
    }
}
