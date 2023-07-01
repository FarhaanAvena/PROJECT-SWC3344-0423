import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class HeroMarketProMax extends JFrame {
    private JTextArea counter1TextArea;
    private JTextArea counter2TextArea;
    private JTextArea counter3TextArea;
    private JTextArea completeTextArea;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;

    private Queue<CustomerInformation> counter1Queue;
    private Queue<CustomerInformation> counter2Queue;
    private Queue<CustomerInformation> counter3Queue;
    private Stack<CustomerInformation> completeStack;

    public HeroMarketProMax() {
        setTitle("HeroMarketProMax Checkout System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        JLabel counter1Label = new JLabel("Counter 1 Queue:");
        counter1Label.setBounds(30, 30, 150, 20);
        add(counter1Label);

        counter1TextArea = new JTextArea();
        JScrollPane counter1ScrollPane = new JScrollPane(counter1TextArea);
        counter1ScrollPane.setBounds(30, 60, 250, 200);
        add(counter1ScrollPane);

        JLabel counter2Label = new JLabel("Counter 2 Queue:");
        counter2Label.setBounds(350, 30, 150, 20);
        add(counter2Label);

        counter2TextArea = new JTextArea();
        JScrollPane counter2ScrollPane = new JScrollPane(counter2TextArea);
        counter2ScrollPane.setBounds(350, 60, 250, 200);
        add(counter2ScrollPane);

        JLabel counter3Label = new JLabel("Counter 3 Queue:");
        counter3Label.setBounds(30, 300, 150, 20);
        add(counter3Label);

        counter3TextArea = new JTextArea();
        JScrollPane counter3ScrollPane = new JScrollPane(counter3TextArea);
        counter3ScrollPane.setBounds(30, 330, 250, 200);
        add(counter3ScrollPane);

        JLabel completeLabel = new JLabel("Completed Payments:");
        completeLabel.setBounds(350, 300, 150, 20);
        add(completeLabel);

        completeTextArea = new JTextArea();
        JScrollPane completeScrollPane = new JScrollPane(completeTextArea);
        completeScrollPane.setBounds(350, 330, 250, 200);
        add(completeScrollPane);

        addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setBounds(30, 540, 120, 30);
        add(addCustomerButton);

        removeCustomerButton = new JButton("Remove Customer");
        removeCustomerButton.setBounds(160, 540, 140, 30);
        add(removeCustomerButton);

        addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        removeCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeCustomer();
            }
        });

        setVisible(true);

        counter1Queue = new LinkedList<>();
        counter2Queue = new LinkedList<>();
        counter3Queue = new LinkedList<>();
        completeStack = new Stack<>();

        processPayments();
    }

    public void processPayments() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("customerList.txt"));

            LinkedList<CustomerInformation> customerList = new LinkedList<>();
            LinkedList<ItemInformation> itemList = new LinkedList<>();

            CustomerInformation cust;
            ItemInformation item;

            String inData = null;

            while ((inData = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(inData, ";");
                String custId = st.nextToken();
                String custIC = st.nextToken();
                String itemId = st.nextToken();
                String itemName = st.nextToken();
                double itemPrice = Double.parseDouble(st.nextToken());
                int quantity = Integer.parseInt(st.nextToken());
                String datePurchase = st.nextToken();

                item = new ItemInformation(itemId, itemName, itemPrice, datePurchase, quantity);
                cust = new CustomerInformation(custId, custIC, 0);
                cust.addItemPurchased(item);

                customerList.add(cust);
                itemList.add(item);
            }

            in.close();

            int counter1Count = 0;
            int counter2Count = 0;

            while (!customerList.isEmpty()) {
                cust = customerList.removeFirst();
                item = cust.getItemsPurchased().get(0);

                if (item.getQuantity() <= 5) {
                    if (counter1Count < 5) {
                        cust.setCounterPaid(1);
                        counter1Queue.add(cust);
                        counter1Count++;
                    } else if (counter2Count < 5) {
                        cust.setCounterPaid(2);
                        counter2Queue.add(cust);
                        counter2Count++;
                    } else {
                        cust.setCounterPaid(1);
                        counter1Queue.add(cust);
                        counter1Count++;
                    }
                } else {
                    cust.setCounterPaid(3);
                    counter3Queue.add(cust);
                }
            }

            displayQueue(counter1Queue, counter1TextArea);
            displayQueue(counter2Queue, counter2TextArea);
            displayQueue(counter3Queue, counter3TextArea);

            while (!counter1Queue.isEmpty() || !counter2Queue.isEmpty() || !counter3Queue.isEmpty()) {
                for (int i = 0; i < 5; i++) {
                    if (!counter1Queue.isEmpty()) {
                        cust = counter1Queue.poll();
                        item = cust.getItemsPurchased().get(0);

                        double totalAmount = item.calculateTotalPrice();

                        displayPayment("Payment at Counter 1", cust, totalAmount, completeStack);
                    }

                    if (!counter2Queue.isEmpty()) {
                        cust = counter2Queue.poll();
                        item = cust.getItemsPurchased().get(0);

                        double totalAmount = item.calculateTotalPrice();

                        displayPayment("Payment at Counter 2", cust, totalAmount, completeStack);
                    }
                }

                if (!counter3Queue.isEmpty()) {
                    for (int i = 0; i < 5; i++) {
                        if (!counter3Queue.isEmpty()) {
                            cust = counter3Queue.poll();
                            item = cust.getItemsPurchased().get(0);

                            double totalAmount = item.calculateTotalPrice();

                            displayPayment("Payment at Counter 3", cust, totalAmount, completeStack);
                        }
                    }
                }

                displayStack(completeStack, completeTextArea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayQueue(Queue<CustomerInformation> queue, JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (CustomerInformation customer : queue) {
            sb.append("Customer ID: ").append(customer.getCustId()).append("\n");
            sb.append("IC Number: ").append(customer.getCustIC()).append("\n");
            sb.append("Counter Paid: ").append(customer.getCounterPaid()).append("\n");
            sb.append("Items Purchased:\n");
            for (ItemInformation item : customer.getItemsPurchased()) {
                sb.append("- ").append(item.getItemName()).append(": RM ").append(item.getItemPrice()).append("\n");
            }
            sb.append("-----------------------\n");
        }
        textArea.setText(sb.toString());
    }

    public void displayStack(Stack<CustomerInformation> stack, JTextArea textArea) {
        StringBuilder sb = new StringBuilder();
        for (CustomerInformation customer : stack) {
            sb.append("Customer ID: ").append(customer.getCustId()).append("\n");
            sb.append("IC Number: ").append(customer.getCustIC()).append("\n");
            sb.append("Counter Paid: ").append(customer.getCounterPaid()).append("\n");
            sb.append("Items Purchased:\n");
            for (ItemInformation item : customer.getItemsPurchased()) {
                sb.append("- ").append(item.getItemName()).append(": RM ").append(item.getItemPrice()).append("\n");
            }
            sb.append("-----------------------\n");
        }
        textArea.setText(sb.toString());
    }

    public void displayPayment(String counter, CustomerInformation customer, double totalAmount, Stack<CustomerInformation> stack) {
        StringBuilder sb = new StringBuilder();
        sb.append("Payment at ").append(counter).append("\n");
        sb.append("Customer ID: ").append(customer.getCustId()).append("\n");
        sb.append("IC Number: ").append(customer.getCustIC()).append("\n");
        sb.append("Total Amount: RM ").append(totalAmount).append("\n");
        sb.append("Items Purchased:\n");
        for (ItemInformation item : customer.getItemsPurchased()) {
            sb.append("- ").append(item.getItemName()).append(": RM ").append(item.getItemPrice()).append("\n");
        }
        sb.append("-----------------------\n");
        completeTextArea.append(sb.toString());
        stack.push(customer);
    }

    public void addCustomer() {
        String customerId = JOptionPane.showInputDialog("Enter Customer ID:");
        String icNumber = JOptionPane.showInputDialog("Enter IC Number:");

        CustomerInformation customer = new CustomerInformation(customerId, icNumber, 0);

        String itemId = JOptionPane.showInputDialog("Enter Item ID:");
        String itemName = JOptionPane.showInputDialog("Enter Item Name:");
        double itemPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Item Price:"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:"));
        String datePurchase = JOptionPane.showInputDialog("Enter Date of Purchase:");

        ItemInformation item = new ItemInformation(itemId, itemName, itemPrice, datePurchase, quantity);

        customer.addItemPurchased(item);

        if (quantity <= 5) {
            if (counter1Queue.size() < 5) {
                customer.setCounterPaid(1);
                counter1Queue.add(customer);
            } else if (counter2Queue.size() < 5) {
                customer.setCounterPaid(2);
                counter2Queue.add(customer);
            } else {
                customer.setCounterPaid(1);
                counter1Queue.add(customer);
            }
        } else {
            customer.setCounterPaid(3);
            counter3Queue.add(customer);
        }

        displayQueue(counter1Queue, counter1TextArea);
        displayQueue(counter2Queue, counter2TextArea);
        displayQueue(counter3Queue, counter3TextArea);
    }

    public void removeCustomer() {
        String counterOption = JOptionPane.showInputDialog("Enter Counter Number to Remove Customer (1/2/3):");
        int counterNumber = Integer.parseInt(counterOption);

        CustomerInformation customer = null;

        if (counterNumber == 1 && !counter1Queue.isEmpty()) {
            customer = counter1Queue.poll();
        } else if (counterNumber == 2 && !counter2Queue.isEmpty()) {
            customer = counter2Queue.poll();
        } else if (counterNumber == 3 && !counter3Queue.isEmpty()) {
            customer = counter3Queue.poll();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Counter Number or Counter Queue is Empty");
            return;
        }

        if (customer != null) {
            displayQueue(counter1Queue, counter1TextArea);
            displayQueue(counter2Queue, counter2TextArea);
            displayQueue(counter3Queue, counter3TextArea);

            JOptionPane.showMessageDialog(null, "Customer Removed Successfully");

            double totalAmount = customer.getItemsPurchased().get(0).calculateTotalPrice();
            displayPayment("Manual Payment at Counter " + counterNumber, customer, totalAmount, completeStack);
            displayStack(completeStack, completeTextArea);
        }
    }

    public static void main(String[] args) {
        new HeroMarketProMax();
    }
}
