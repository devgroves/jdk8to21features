package DataStructures;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

// solution

class Solution {

    public static int[] inOrder(Node root) {
        int[] ln = null;
        int llength = 0;
        if (root.left != null) {
            ln = inOrder(root.left);
            llength = ln.length;
        }
        int val = root.val;
        int[] rn = null;
        int rlength = 0;
        if (root.right != null) {
            rn = inOrder(root.right);
            rlength = rn.length;
        }

        int[] result = new int[llength+1+rlength];
        System.out.println(" root " + root.val + " result length " + result.length);
        if (llength >0) {
            System.arraycopy(ln, 0, result, 0, llength);
        }
        result[llength++] = val;
        if (rlength > 0) {
            System.arraycopy(rn, 0, result, llength, rlength);
        }

        return result;
    }

    public static int sumOfRightLeaves(Node root) {
        // fill in
        // with solution
        if (root == null) return 0;
        LinkedList<Node> nodesList =
                new LinkedList<Node>();
        nodesList.add(root);
        ArrayList<Node> rightNodes = new ArrayList<>();
        int sum = 0;
        while (!nodesList.isEmpty()) {
            Node node = nodesList.removeFirst();
            if (node.left != null) {
                nodesList.add(node.left);
            }
            if (node.right != null) {
                nodesList.add(node.right);
                rightNodes.add(node.right);
            }
            if (node.left == null && node.right == null && rightNodes.contains(node)) {
                sum += node.val;
            }
        }
        return sum;
    }

    public static int lowestCommonAncestor(Node root, int node1, int node2) {
        // fill in
        // with solution
        List<Integer> parents1 = findParentValues(root, node1);
        List<Integer> parents2 = findParentValues(root, node2);
        if (parents1 == null || parents2 == null) return 0;
        parents1.retainAll(parents2);
        return parents1.getLast();
    }
    public static List<Integer> findParentValues(Node root, int searchVal) {
        List<Integer> parents = new ArrayList<>();
        Node currentNode = root;
        boolean isFound = false;
        while (currentNode != null) {
            parents.add(currentNode.val);
            if (currentNode.val == searchVal) {
                isFound = true;
                break;
            }
            if (currentNode.val < searchVal) {
                currentNode = currentNode.right;
            }
            if (currentNode.val > searchVal) {
                currentNode = currentNode.left;
            }
        }
        return isFound ? parents : null;
    }

    // print your findings using this function!
    public static void log() {
        System.out.println(lowestCommonAncestor(new Node(0), 0, 0));
    }

}


// available data structures

class Node {
    public int val;

    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }
}

public class LowestCommonAncestor {

    private Node tree1;
    private Node tree2;
    private Node tree3;
    private Node tree4;
    private Node tree5;

    @Before
    public void setUp() {
        // Regular binary trees
        tree1 = new Node(4);
        tree1.left = new Node(1);
        tree1.right = new Node(3);

        tree2 = new Node(5);
        tree2.left = new Node(10);
        tree2.left.left = new Node(17);
        tree2.left.right = new Node(3);
        tree2.right = new Node(8);

        // Binary search trees
        tree3 = new Node(6);
        tree3.left = new Node(3);

        tree4 = new Node(5);
        tree4.left = new Node(3);
        tree4.left.left = new Node(2);
        tree4.left.left.left = new Node(1);

        tree5 = new Node(8);
        tree5.left = new Node(6);
        tree5.right = new Node(9);
        tree5.left.left = new Node(5);
        tree5.left.right = new Node(7);
        tree5.right.right = new Node(10);
    }

    // available data structures

    // tests

    @Test
    public void leftChildsOnlyTreeTest() {
        try {
            int[] out = Solution.inOrder(tree4);
            System.out.println(Arrays.toString(out));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertEquals(Solution.lowestCommonAncestor(tree4, 1, 3), 3);
    }

    @Test
    public void largeTreeRootTest() {
        assertEquals(Solution.lowestCommonAncestor(tree5, 5, 10), 8);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(LowestCommonAncestor.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

}

