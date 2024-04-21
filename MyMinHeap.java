import java.util.Comparator;

public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;
    private Comparator<T> comparator;

    // Constructor without comparator
    public MyMinHeap() {
        this(null);
    }

    // Constructor with comparator
    public MyMinHeap(Comparator<T> comparator) {
        this.heap = new MyArrayList<>();
        this.comparator = comparator;
    }

    // Checks if the heap is empty
    public boolean empty() {
        return heap.size() == 0;
    }

    // Returns the size of the heap
    public int size() {
        return heap.size();
    }

    // Gets the minimum element from the heap
    public T getMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    // Extracts the minimum element from the heap
    public T extractMin() {
        if (empty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = getMin();
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(0);
        return min;
    }

    // Inserts an element into the heap
    public void insert(T element) {
        heap.add(element);
        traverseUp(heap.size() - 1);
    }

    // Maintains the heap property starting from the given index
    private void heapify(int index) {
        int left = leftChildOf(index);
        int right = rightChildOf(index);
        int smallest = index;

        if (left < heap.size() && compare(heap.get(left), heap.get(index)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Moves the element up the heap until the heap property is satisfied
    private void traverseUp(int index) {
        while (index != 0 && compare(heap.get(index), heap.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    // Returns the index of the left child of the given index
    private int leftChildOf(int index) {
        return 2 * index + 1;
    }

    // Returns the index of the right child of the given index
    private int rightChildOf(int index) {
        return 2 * index + 2;
    }

    // Returns the index of the parent of the given index
    private int parentOf(int index) {
        return (index - 1) / 2;
    }

    // Swaps the elements at the given indices in the heap
    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Compares two elements using the comparator if provided, otherwise uses natural ordering
    private int compare(T first, T second) {
        if (comparator != null) {
            return comparator.compare(first, second);
        } else {
            return first.compareTo(second);
        }
    }
}
