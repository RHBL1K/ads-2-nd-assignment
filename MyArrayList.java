import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;

    // Default constructor initializes MyArrayList with default capacity
    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // Constructor with capacity parameter creates MyArrayList with specified capacity
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        array = (T[]) new Object[capacity];
        size = 0;
    }

    // Returns the current size of the array
    @Override
    public int size() {
        return size;
    }

    // Checks if the array needs resizing and does so if necessary
    public void checkForCapacity(){
        if(size == array.length){
            T[] newArray = (T[]) new Object[size * 2 + 1]; // Increase capacity by doubling plus one to handle zero initial capacity
            for(int i = 0; i<size; i++){
                newArray[i] = (T) array[i];
            }
            array = newArray;
        }
    }

    // Adds an element to the end of the array
    @Override
    public void add(T item){
        checkForCapacity();
        array[size] = item;
        size++;
    }

    // Adds an element to the beginning of the array by shifting existing elements to the right
    @Override
    public void addFirst(T item){
        checkForCapacity();
        for( int i = size; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = item;
        size++;
    }

    // Adds an element to the end of the array
    @Override
    public void addLast(T item){
        checkForCapacity();
        array[size] = item;
        size++;
    }

    // Throws an exception if the index is invalid
    private void throwException(int index){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    // Throws NoSuchElementException if the array is empty
    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
    }

    // Adds an element at a specified index by shifting existing elements to the right
    @Override
    public void add(int index, T item){
        throwException(index);
        checkForCapacity();
        for(int i = size; i>index; i--){
            array[i] = array[i-1];
        }
        array[index] = item;
        size++;
    }

    // Removes an element at a specified index by shifting existing elements to the left
    @Override
    public void remove(int index){
        throwException(index);
        for( int i = index; i<size-1; i++){
            array[i] = array[i+1];
        }
        array[size - 1] = null; // Prevents memory leak
        size--;
    }

    // Removes the first element of the array by shifting all elements to the left
    @Override
    public void removeFirst() {
        checkEmpty();
        for (int i = 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[--size] = null;
    }

    // Removes the last element of the array by setting it to null
    @Override
    public void removeLast() {
        checkEmpty();
        array[--size] = null;
    }

    // Obtains the element at a specified index
    @Override
    public T get(int index){
        throwException(index);
        return (T) array[index];
    }

    // Retrieves the first element of the array
    @Override
    public T getFirst(){
        checkEmpty();
        return (T) array[0];
    }

    // Retrieves the last element of the array
    @Override
    public T getLast(){
        checkEmpty();
        return (T) array[size-1];
    }

    // Clears the array and resets size to 0
    @Override
    public void clear(){
        for( int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
    }

    // Converts the array to an object array
    @Override
    public Object[] toArray(){
        Object[] result = new Object[size];
        for( int i = 0; i< size; i++){
            result[i] = array[i];
        }
        return result;
    }

    // Creates and returns an iterator for the array
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indx = 0;

            // Checks if the array has the next element
            @Override
            public boolean hasNext() {
                return indx < size;
            }

            // Adds the element as the next one and the iterator moves forward one step
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) array[indx++];
            }
        };
    }

    // Sets the input element with the given index
    @Override
    public Object set(int index, T item){
        throwException(index);
        return array[index] = item;
    }

    // Checks if a specific element object exists in the list
    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null && object == null) {
                return true;  // Both are null
            } else if (array[i] != null && array[i].equals(object)) {
                return true;  // Not null and equal
            }
        }
        return false;
    }

    // Sorts the array
    @Override
    public void sort() {
        T temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Returns the index of the first occurrence of the indicated element in this array, or -1 if this list does not exist
    @Override
    public int indexOf(Object object){
        if( object == null){
            for (int i = 0; i < size; i++)
            {
                if (array[i] == null)
                    return i;
            }
        }else
        {
            for (int i = 0; i < size; i++)
            {
                if(object.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    // Returns the last index of the specified object in this list, or -1 if the list does not contain the object
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                    // return index of null element
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(array[i])) {
                    return i;
                    // return index of matching element
                }
            }
        }
        return -1;
        // return -1 if no match is found
    }
}
