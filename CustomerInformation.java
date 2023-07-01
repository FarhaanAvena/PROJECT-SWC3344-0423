import java.util.LinkedList;

// CustomerInformation class
class CustomerInformation {
    private String custId; // Customer ID
    private String custIC; // Customer IC number
    private int counterPaid; // Counter number where  customer made payment
    private LinkedList<ItemInformation> itemsPurchased; // List of items purchased by  customer

    // Constructor with parameter
    public CustomerInformation(String custId, String custIC, int counterPaid) {
        this.custId = custId; 
        this.custIC = custIC; 
        this.counterPaid = counterPaid; 
        this.itemsPurchased = new LinkedList<>(); 
    }

    // Get customer ID
    public String getCustId() {
        return custId; // Return  customer ID
    }

    // Get customer IC number
    public String getCustIC() {
        return custIC; // Return  customer IC number
    }

    // Get counter number where  customer made payment
    public int getCounterPaid() {
        return counterPaid; // Return  counter number where  customer made payment
    }

    // Set counter number where  customer made payment
    public void setCounterPaid(int counterPaid) {
        this.counterPaid = counterPaid; // Set  counterPaid variable to  provided counter number
    }

    // Get linked list of items purchased by  customer
    public LinkedList<ItemInformation> getItemsPurchased() {
        return itemsPurchased; // Return  linked list of items purchased by  customer
    }

    // Add an item to  list of items purchased
    public void addItemPurchased(ItemInformation item) {
        itemsPurchased.add(item); // Add  provided item to  list of items purchased
    }

    // toString() method
    public String toString() {
        return "Customer ID: " + custId + ", IC: " + custIC + ", Counter Paid: " + counterPaid; 
        // toString for customer ID, IC number, and counter number where payment was made
    }
}
