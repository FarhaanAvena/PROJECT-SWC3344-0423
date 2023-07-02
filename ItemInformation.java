public class ItemInformation {

    private String itemId; // Item ID
    private String itemName; // Item name
    private double itemPrice; // Item price
    private String datePurchase; // Date of purchase
    
    // Constructor without parameter
    ItemInformation() {
        itemId = "";
        itemName = "";
        itemPrice = 0.0;
        datePurchase = "";
    }

    // Constructor with parameter
    public ItemInformation(String itemId, String itemName, double itemPrice, String datePurchase) {
        this.itemId = itemId; 
        this.itemName = itemName; 
        this.itemPrice = itemPrice; 
        this.datePurchase = datePurchase; 
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

    // Set item price
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice; 
    }

    // Set date of purchase
    public void setDatePurchase(String datePurchase) {
        this.datePurchase = datePurchase; 
    }

}
