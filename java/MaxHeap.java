import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxHeap {

    private List<Integer> heap = new ArrayList();

    public void insert(int value) {
        heap.add(value);
        perculateUp(heap.size() - 1);
    }

    public int getMax() {
        return heap.get(0);
    }

    public int deleteMax() {
        swap(heap.size() - 1, 0); // Put max at end of the array
        int deletedMax = heap.remove(heap.size() - 1);
        perculateDown(0);
        return deletedMax;
    }

    public int size() {
        return heap.size();
    }

    private void perculateUp(int index) {

        if (index == 0) {
            return;
        }
        int parentIndex = (index - 1) / 2;
        int currentValue = heap.get(index);
        int parentValue = heap.get(parentIndex);

        if (currentValue >= parentValue) {
            swap(index, parentIndex);
            perculateUp(parentIndex);
        }
    }

    private void perculateDown(int index) {
        if (heap.size() == 0) {
            return;
        }

        int currentValue = heap.get(index);
        int leftValue = getLeftValue(index);
        int leftIndex = (index * 2) + 1;
        int rightValue = getRightValue(index);
        int rightIndex = (index * 2) + 2;

        if (leftValue > rightValue) {
            if (leftValue > currentValue) {
                swap(leftIndex, index);
                perculateDown(leftIndex);
            }
        } else if (leftValue < rightValue) {
            if (rightValue > currentValue) {
                swap(rightIndex, index);
                perculateDown(rightIndex);
            }
        }

    }

    private int getLeftValue(int index) {
        int leftIndex = (index * 2) + 1;
        if (leftIndex >= heap.size()) {
            return -1;
        }
        return heap.get((index * 2) + 1);
    }

    private int getRightValue(int index) {
        int rightIndex = (index * 2) + 2;
        if (rightIndex >= heap.size()) {
            return -1;
        }
        return heap.get((index * 2) + 2);
    }

    private void swap(int index, int parentIndex) {
        int temp = heap.get(index);
        heap.set(index, heap.get(parentIndex));
        heap.set(parentIndex, temp);
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        int[] input = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        for(int i: input) {
            maxHeap.insert(i);
        }
        PriorityQueue<Float> queue = new PriorityQueue<Float>(10, (movie1, movie2) -> Float.compare(movie2, movie1));
        System.out.println(maxHeap.size());
        System.out.println(maxHeap.getMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.size());
        System.out.println(maxHeap.getMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.size());
        System.out.println(maxHeap.getMax());
        System.out.println(maxHeap.deleteMax());
        System.out.println(maxHeap.size());
        System.out.println(maxHeap.getMax());
    }


}
