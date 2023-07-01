// ItemInformation class
class ItemInformation {

    private String itemId; // Item ID
    private String itemName; // Item name
    private double itemPrice; // Item price
    private String datePurchase; // Date of purchase
    private int quantity; // Quantity of items

    // Constructor with parameter
    public ItemInformation(String itemId, String itemName, double itemPrice, String datePurchase, int quantity) {
        this.itemId = itemId; 
        this.itemName = itemName; 
        this.itemPrice = itemPrice; 
        this.datePurchase = datePurchase; 
        this.quantity = quantity; 
    }

    // Get item ID
    public String getItemId() {
        return itemId; 
    }

    // Get item name
    public String getItemName() {
        return itemName; 
    }

    // Get item price
    public double getItemPrice() {
        return itemPrice; 
    }

    // Get date of purchase
    public String getDatePurchase() {
        return datePurchase; 
    }

    // Get quantity of items
    public int getQuantity() {
        return quantity; 
    }

    // Set item price
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice; 
    }

    // Set date of purchase
    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase; 
    }

    // Calculate total price of  items
    public double calculateTotalPrice() {
        return itemPrice * quantity; // Multiply  item price by  quantity of items and return  result
    }

    // toString() method for item information
    public String toString() {
        return "Item ID: " + itemId + ", Name: " + itemName + ", Price: " + itemPrice + ", Date Purchased: " + datePurchase;
        // toString for item ID, name, price, and date of purchase
    }
}
