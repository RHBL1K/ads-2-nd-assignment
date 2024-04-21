public class MyQueue<T> {

    // Initializing a linked list to store elements
    private MyLinkedList<T> list = new MyLinkedList<>();

    // Method to add an item to the end of the queue
    public T enqueue(T item) {
        list.addLast(item);
        return item;
    }

    // Method to remove and return the first item from the queue
    public T dequeue() {
        T removingItem = peek(); // Saving the first item to return later
        list.removeFirst(); // Removing the first item
        return removingItem; // Returning the removed item
    }

    // Method to get the first item from the queue without removing it
    public T peek() {
        if (isEmpty()) {
            return null; // or throw an exception if the queue is empty
        }
        return list.getFirst();
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return list.size() == 0;
    }

    // Method to return the size of the queue
    public int size() {
        return list.size();
    }
}