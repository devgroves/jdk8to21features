package trees;

import java.util.ArrayList;

public class MinHeap {
    ArrayList<Integer> heapData = new ArrayList<>();

    // 2, 3, 4, 1
    public void insert(int data) {
        int currSize = heapData.size();
        heapData.add(data);
        int currPos = currSize;
        heapify(currPos);
    }

    private void heapify(int currPos) {
        int parentPos = findParentPos(currPos);
        while (heapData.get(parentPos) > heapData.get(currPos)) {
            System.out.println(parentPos+ " " + currPos);
            swap(parentPos, currPos);
            currPos = parentPos;
            parentPos = findParentPos(currPos);
            if (parentPos < 0) break;
        }
    }

    private int findParentPos(int pos) {
        return (pos - 1)/2;
    }

    private void swap(int pos1, int pos2) {
        int value = heapData.get(pos1);
        heapData.set(pos1, heapData.get(pos2));
        heapData.set(pos2, value);
    }


    public int extractMin() {
        int data = this.heapData.get(0);
        this.heapData.set(this.heapData.size()-1, this.heapData.get(1));
        this.heapify(this.heapData.size()-1);
        this.heapData.remove(this.heapData.size()-1);
        return data;
    }

    public void printHeap() {
        for (int i=0; i<heapData.size(); i++) {
            System.out.print(heapData.get(i) + ",");
        }
        System.out.println("***");
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(3);
        minHeap.insert(4);
        minHeap.printHeap();
        minHeap.insert(2);
        minHeap.printHeap();
        minHeap.insert(1);
        minHeap.printHeap();
        System.out.println(minHeap.extractMin());
        minHeap.printHeap();
    }
}
