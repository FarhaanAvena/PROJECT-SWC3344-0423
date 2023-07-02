import java.util.LinkedList;

public class Queue {
    private LinkedList<Object> queue; // Declare the object of queue using LinkedList

    // Constructor without parameter
    public Queue() {
        queue = new LinkedList<>();
    }

    // Method to insert an object to the queue
    public void enqueue(Object item) {
        queue.addLast(item);
    }

    // Method to remove an object from the queue
    public Object dequeue() {
        if (!empty()) {
            return queue.removeFirst(); // Remove the first element (front)
        } else {
            System.out.println("Queue is empty!");
            return null; // Return null or throw an exception to handle empty queue case
        }
    }

    // Method to test whether the queue is empty or not
    public boolean empty() {
        return queue.isEmpty();
    }

    // Return the element at the front without removing it
    public Object front() {
        if (!empty()) {
            return queue.getFirst();
        } else {
            System.out.println("Queue is empty");
            return null; // Return null or throw an exception to handle empty queue case
        }
    }

    // Method to get the size of the queue
    public int size() {
        return queue.size();
    }
}
