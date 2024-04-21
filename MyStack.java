public class MyStack<T>{

    // Initializing a list to store elements
    private MyArrayList<T> list = new MyArrayList<>();

    // Method to add a new element to the top of the stack
    public T push(T item) {
        list.add(item);
        return item;
    }

    // Method to retrieve the top element of the stack without removing it
    public T peek(){
        if (empty()) {
            return null; // or throw an exception if the stack is empty
        }
        return list.get(0);
    }

    // Method to remove and return the top element of the stack
    public T pop(){
        if (empty()) {
            return null; // or throw an exception if the stack is empty
        }
        T removingItem = peek(); // Saving the top element to return later
        list.removeFirst(); // Removing the top element
        return removingItem; // Returning the removed element
    }

    // Method to check if the stack is empty
    public boolean empty(){
        return list.size() == 0;
    }

    // Method to return the size of the stack
    public int size() {
        return list.size();
    }
}