import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class Gui implements ActionListener {
    private JFrame frame;
    private JPanel panelCounter1;
    private JPanel panelCounter2;
    private JPanel panelCounter3;
    private JLabel labelQueue1, labelQueue2, labelQueue3, labelCustomerLeft;
    private JButton btnPay1, btnPay2, btnPay3, btnNext, btnDisplay, btnReset;
    private int queue1 = 0, queue2 = 0, queue3 = 0;
    private int totalCustomer;
    private Queue qCounter1, qCounter2, qCounter3;
    private LinkedList<CustomerInformation> customerList;
    private Stack<CustomerInformation> completedStack;

    public Gui(LinkedList<CustomerInformation> customerList, Queue qCounter1, Queue qCounter2, Queue qCounter3,
    int totalCustomer, Stack<CustomerInformation> completedStack) {
        this.customerList = customerList;
        this.totalCustomer = totalCustomer;
        this.qCounter1 = qCounter1;
        this.qCounter2 = qCounter2;
        this.qCounter3 = qCounter3;
        this.completedStack = completedStack;

        // Panel Counter 1
        panelCounter1 = new JPanel();
        panelCounter1.setBorder(new LineBorder(Color.BLACK));
        JLabel labelCounter1 = new JLabel("Counter 1");
        labelCounter1.setFont(new Font("Verdana", 1, 15));
        labelQueue1 = new JLabel("Queue: " + String.valueOf(queue1));
        panelCounter1.add(labelCounter1);
        panelCounter1.add(labelQueue1);
        btnPay1 = new JButton("Pay");
        panelCounter1.add(btnPay1);

        // Panel Counter 2
        panelCounter2 = new JPanel();
        panelCounter2.setBorder(new LineBorder(Color.BLACK));
        JLabel labelCounter2 = new JLabel("Counter 2");
        labelCounter2.setFont(new Font("Verdana", 1, 15));
        labelQueue2 = new JLabel("Queue: " + String.valueOf(queue2));
        panelCounter2.add(labelCounter2);
        panelCounter2.add(labelQueue2);
        btnPay2 = new JButton("Pay");
        panelCounter2.add(btnPay2);

        // Panel Counter 3
        panelCounter3 = new JPanel();
        panelCounter3.setBorder(new LineBorder(Color.BLACK));
        JLabel labelCounter3 = new JLabel("Counter 3");
        labelCounter3.setFont(new Font("Verdana", 1, 15));
        labelQueue3 = new JLabel("Queue: " + String.valueOf(queue3));
        panelCounter3.add(labelCounter3);
        panelCounter3.add(labelQueue3);
        btnPay3 = new JButton("Pay");
        panelCounter3.add(btnPay3);

        // Button NEXT
        btnNext = new JButton("LOAD / NEXT");
        labelCustomerLeft = new JLabel("Customers Left: " + String.valueOf(totalCustomer));

        // Button to display completed customer
        btnDisplay = new JButton("DISPLAY");

        // Button to reset
        btnReset = new JButton("RESET");

        // Logo
        ImageIcon logoIcon = new ImageIcon("HeroMarketProMax.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        // Panel Counters
        JPanel panelCounters = new JPanel(new GridLayout(1, 3, 10, 10));
        panelCounters.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCounters.add(panelCounter1);
        panelCounters.add(panelCounter2);
        panelCounters.add(panelCounter3);

        // Panel Buttons
        JPanel panelButtons = new JPanel(new GridLayout(1, 2, 10, 10));
        panelButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelButtons.add(btnNext);
        panelButtons.add(btnReset);

        // Panel Display
        JPanel panelDisplay = new JPanel(new GridLayout(2, 1, 10, 10));
        panelDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDisplay.add(labelCustomerLeft);
        panelDisplay.add(btnDisplay);

        // Main Panel
        JPanel panelMain = new JPanel(new BorderLayout(10, 10));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.add(panelCounters, BorderLayout.NORTH);
        panelMain.add(panelButtons, BorderLayout.CENTER);
        panelMain.add(panelDisplay, BorderLayout.SOUTH);

        // Frame
        frame = new JFrame();
        frame.setTitle("HeroMarketProMax");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 570);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.add(logoLabel, BorderLayout.CENTER);
        frame.add(panelMain, BorderLayout.SOUTH);
        frame.setVisible(true);

        btnPay1.addActionListener(this);
        btnPay2.addActionListener(this);
        btnPay3.addActionListener(this);
        btnNext.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnReset.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Iterator<CustomerInformation> iterator = customerList.iterator();
        CustomerInformation cust;
        if (e.getSource() == btnNext) { // start btnNext

            // while the list is not empty, run this code until no more in the list
            while (iterator.hasNext()) {
                CustomerInformation customer = iterator.next();

                if (customer.getItemQuantity() <= 5) {
                    if (qCounter1.size() < 5) {
                        iterator.remove();
                        qCounter1.enqueue(customer);
                        customer.setCounterPaid(1);
                        totalCustomer--;
                        queue1++;
                        labelQueue1.setText("Queue: " + String.valueOf(queue1));
                    } else if (qCounter2.size() < 5) {
                        iterator.remove();
                        qCounter2.enqueue(customer);
                        customer.setCounterPaid(2);
                        totalCustomer--;
                        queue2++;
                        labelQueue2.setText("Queue: " + String.valueOf(queue2));
                    }
                } else {
                    if (qCounter3.size() < 5) {
                        iterator.remove();
                        qCounter3.enqueue(customer);
                        customer.setCounterPaid(3);
                        totalCustomer--;
                        queue3++;
                        labelQueue3.setText("Queue: " + String.valueOf(queue3));
                    } else {
                        JOptionPane.showMessageDialog(null, "Kaunter Dah Penuh Woi !!");
                        break;
                    }
                }
            }
            labelCustomerLeft.setText("Customers Left: " + String.valueOf(totalCustomer));
        } // close btnNext

        if (e.getSource() == btnPay1) {
            if (!qCounter1.empty()) {
                cust = (CustomerInformation) qCounter1.dequeue();
                JOptionPane.showMessageDialog(null, "Customer with ID " + cust.getCustId() + " has paid");
                queue1--;
                labelQueue1.setText("Queue: " + queue1);
                completedStack.push(cust);
            } else {
                JOptionPane.showMessageDialog(null, "No customers at Counter 1. Load customers lah!");
            }
        }

        if (e.getSource() == btnPay2) {
            if (!qCounter2.empty()) {
                cust = (CustomerInformation) qCounter2.dequeue();
                JOptionPane.showMessageDialog(null, "Customer with ID " + cust.getCustId() + " has paid");
                queue2--;
                labelQueue2.setText("Queue: " + queue2);
                completedStack.push(cust);
            } else {
                JOptionPane.showMessageDialog(null, "No customers at Counter 2. Load customers lah!");
            }
        }

        if (e.getSource() == btnPay3) {
            if (!qCounter3.empty()) {
                cust = (CustomerInformation) qCounter3.dequeue();
                JOptionPane.showMessageDialog(null, "Customer with ID " + cust.getCustId() + " has paid");
                queue3--;
                labelQueue3.setText("Queue: " + queue3);
                completedStack.push(cust);
            } else {
                JOptionPane.showMessageDialog(null, "No customers at Counter 3. Load customers lah!");
            }
        }

        if (e.getSource() == btnDisplay) {
            while (!completedStack.isEmpty()) {
                cust = (CustomerInformation) completedStack.pop();
                System.out.println(cust);
            }
        }

        if (e.getSource() == btnReset) {
            // Reset all queues, counters, and labels
            while (!qCounter1.empty()) {
                qCounter1.dequeue();
            }
            while (!qCounter2.empty()) {
                qCounter2.dequeue();
            }
            while (!qCounter3.empty()) {
                qCounter3.dequeue();
            }
            totalCustomer = customerList.size();
            queue1 = 0;
            queue2 = 0;
            queue3 = 0;
            labelQueue1.setText("Queue: " + String.valueOf(queue1));
            labelQueue2.setText("Queue: " + String.valueOf(queue2));
            labelQueue3.setText("Queue: " + String.valueOf(queue3));
            labelCustomerLeft.setText("Customers Left: " + String.valueOf(totalCustomer));
            completedStack.clear();
        }

    } // end class

    public static void main(String[] args) {
        LinkedList<CustomerInformation> customerList = new LinkedList<>();
        Queue qCounter1 = new Queue();
        Queue qCounter2 = new Queue();
        Queue qCounter3 = new Queue();
        Stack<CustomerInformation> completedStack = new Stack<>();
        int totalCustomer = 0;

        Gui gui = new Gui(customerList, qCounter1, qCounter2, qCounter3, totalCustomer, completedStack);
    }
}