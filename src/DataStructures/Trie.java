package DataStructures;

import java.util.HashMap;
import java.util.Stack;

public class Trie {
    public static class TNode {
        HashMap<Character, TNode> nodeList = new HashMap<>();
        public boolean isCharPresent(Character c) {
            return this.nodeList.containsKey(c);
        }
        public void setEndNode() {
            this.nodeList.put('*', null);
        }
        public TNode getNodeForChar(Character c) {
            return this.nodeList.get(c);
        }

        public TNode addChar(Character c) {
            TNode newNode = new TNode();
            this.nodeList.put(c, newNode);
            return newNode;
        }


        public Integer findLength() {
            return this.nodeList.size();
        }


        public int cleanNodeOrChar(Character c) {
            this.nodeList.put(c, null);
            if (this.nodeList.size() == 1) {
                this.nodeList.clear();
            }
            System.out.println("clean node or char " + c + " size " + this.nodeList.size());
            return this.nodeList.size();
        }
    }
    private TNode head;
    public Trie() {
        head = new TNode();
    }

    public void add(String val) {
        TNode nextNode = this.head;
        for (int i=0; i<val.length(); i++) {
            Character char1 = val.charAt(i);
            if (nextNode.isCharPresent(char1)) {
                nextNode = nextNode.getNodeForChar(char1);
            } else {
                nextNode = nextNode.addChar(char1);
            }
        }
        nextNode.setEndNode();
    }

    public int search(String val) {
        TNode nextNode = this.head;
        int depth = 0;
        for (int i=0; i<val.length(); i++) {
            Character char1 = val.charAt(i);
            if (nextNode.isCharPresent(char1)) {
                nextNode = nextNode.getNodeForChar(char1);
                depth++;
            } else {
                depth = -1;
                break;
            }
        }
        return depth;
    }

    public boolean remove(String val) {
        boolean isRemoved = false;
        TNode nextNode = this.head;
        Stack<TNode> stackNodes = new Stack<>();
        int i = 0;
        // loop the word to find the nodes to be cleaned up...
        for (; i<val.length(); i++) {
            Character c = val.charAt(i);
            if (nextNode.isCharPresent(c)) {
                int length = nextNode.findLength();
                if (length > 1) {
                    stackNodes.clear();
                }
                stackNodes.push(nextNode);
                nextNode = nextNode.getNodeForChar(c);
            } else {
                break;
            }
        }
        System.out.println("stack Nodes" + stackNodes);
        System.out.println("i value " + i);
        // in the eow cleanup starts and keep travelling back...
        if (nextNode.isCharPresent('*')) {
            stackNodes.push(nextNode);
            stackNodes.pop().cleanNodeOrChar('*'); // remove eow char
            while (!stackNodes.empty()) {
                i = i -1;
                if (stackNodes.pop().cleanNodeOrChar(val.charAt(i)) != 0) {
                    System.out.println("multi char in map" + val.charAt(i));
                }
            }
            isRemoved = true;
        }
        return isRemoved;
    }

    public static void main(String[] args) {
        Trie t1 = new Trie();
        t1.add("cat");
        System.out.println("search value" + t1.search("cat"));
        t1.remove("cat");
        System.out.println("search value" + t1.search("cat"));
    }
}
